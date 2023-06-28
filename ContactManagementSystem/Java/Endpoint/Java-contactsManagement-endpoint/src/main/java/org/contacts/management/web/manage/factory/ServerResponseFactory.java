package org.contacts.management.web.manage.factory;

import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log
public class ServerResponseFactory {
    private ServerResponseFactory() {
    }

    public static Mono<ServerResponse> ok(final ServerRequest request, final Object content) {
        return ServerResponse.ok()
                .contentType(request.headers().contentType().orElse(MediaType.APPLICATION_JSON))
                .bodyValue(content);
    }
}
