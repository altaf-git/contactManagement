package org.contacts.management.web.manage.handler;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.web.manage.model.SaveRequest;
import org.contacts.management.web.manage.service.ManagementService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Log
@AllArgsConstructor
public class ContactsHandler extends AbstractBaseHandler {

    private final ManagementService service;

    public Mono<ServerResponse> save(final ServerRequest request) {
        return super.processRequestForServerResponseMono(request, SaveRequest.class, service::saveContact);
    }

    public Mono<ServerResponse> delete(final ServerRequest request) {
        String phoneNumber = request.queryParam("phoneNumber").orElse("");
        return service.deleteContact(phoneNumber)
                .flatMap(contact -> ServerResponse.ok().bodyValue(contact))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByFirstName(ServerRequest request) {
        String firstName = request.queryParam("firstName").orElse("");
        return service.getContactByFirstName(firstName)
                .flatMap(contact -> ServerResponse.ok().bodyValue(contact))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByLastName(ServerRequest request) {
        String lastName = request.queryParam("lastName").orElse("");
        return service.getContactByLastName(lastName)
                .flatMap(contact -> ServerResponse.ok().bodyValue(contact))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByEmail(ServerRequest request) {
        String email = request.queryParam("email").orElse("");
        return service.getContactByEmail(email)
                .flatMap(contact -> ServerResponse.ok().bodyValue(contact))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateByEmail(final ServerRequest request) {
        return super.processRequestForServerResponseMono(request, SaveRequest.class, service::updateByEmail);
    }

    public Mono<ServerResponse> updateByPhoneNumber(final ServerRequest request) {
        return super.processRequestForServerResponseMono(request, SaveRequest.class, service::updateByPhoneNumber);
    }
}