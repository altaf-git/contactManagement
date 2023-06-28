package org.contacts.management.web.manage.handler;

import lombok.extern.java.Log;
import org.contacts.management.dslentities.exceptions.ClientSideException;
import org.contacts.management.dslentities.exceptions.ErrorBody;
import org.contacts.management.dslentities.exceptions.ServerSideException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Log
public abstract class AbstractBaseHandler implements HealthCheckAware {


    protected <T, R> Mono<ServerResponse> processRequestForServerResponseMono(final ServerRequest request,
                                                                              final Class<T> requestBodyClass,
                                                                              final Function<T, Mono<R>> transformer) {
        return extractBody(request, requestBodyClass)
                .flatMap(body -> transformer.apply(body)
                        .flatMap(response -> ServerResponse.ok().bodyValue(response))
                        .switchIfEmpty(Mono.error(new ControllerException(400, "Empty Body", "The Body is Empty")))
                )
                .switchIfEmpty(Mono.error(new ControllerServerException(500, "Server Error", "Some internal error")));
    }

    private <T> Mono<T> extractBody(final ServerRequest request, final Class<T> requestBodyClass) {
        switch (Objects.requireNonNull(request.method())) {
            case PUT, POST -> {
                return request.bodyToMono(requestBodyClass)
                        .doOnEach(ignore -> log.info("Extracted body from request.. will hand over to business logic"))
                        .switchIfEmpty(Mono.error(new ControllerException(UNPROCESSABLE_ENTITY.value(), "", "Empty body")))
                        .onErrorMap(clientError());
            }
        }
        return extractFromRequest(request, requestBodyClass);
    }

    protected <T> Mono<T> extractFromRequest(final ServerRequest request, final Class<T> requestBodyClass) {
        return Mono.just(0)
                .doOnEach(ignore -> log.info(String.format("Potential unhandled scenario method=%s path=%s", request.method(), request.path())))
                .then(Mono.empty());
    }

    private Function<Throwable, Throwable> clientError() {
        return t -> {
            if (t instanceof ClientSideException) {
                return t;
            }
            if (t instanceof ResponseStatusException e) {
                if (e.getStatus().is4xxClientError()) {
                    return new HttpClientErrorException(e.getStatus());
                }
                return t;
            }
            return new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        };
    }

    static class ControllerException extends ClientSideException {
        public ControllerException(int statusCode, String code, String msg) {
            super(statusCode, createErrorBody(statusCode, code, msg));
        }

        private static ErrorBody createErrorBody(int statusCode, String code, String msg) {
            ErrorBody errorBody = new ErrorBody();
            errorBody.setHttpStatus(statusCode);
            errorBody.setCode(code);
            errorBody.setMessage(msg);
            return errorBody;
        }
    }

    static class ControllerServerException extends ServerSideException {
        public ControllerServerException(int statusCode, String code, String msg) {
            super(statusCode, createErrorBody(statusCode, code, msg));
        }

        private static ErrorBody createErrorBody(int statusCode, String code, String msg) {
            ErrorBody errorBody = new ErrorBody();
            errorBody.setHttpStatus(statusCode);
            errorBody.setCode(code);
            errorBody.setMessage(msg);
            return errorBody;
        }
    }
}