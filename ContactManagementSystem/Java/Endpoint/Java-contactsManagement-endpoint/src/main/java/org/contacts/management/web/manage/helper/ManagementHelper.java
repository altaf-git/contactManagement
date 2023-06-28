package org.contacts.management.web.manage.helper;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.dslentities.contact.ContactEntity;
import org.contacts.management.logic.manage.actions.ContactsAction;
import org.contacts.management.web.manage.factory.ContactResponseFactory;
import org.contacts.management.web.manage.model.SaveRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Log
public class ManagementHelper {

    private final ContactsAction action;

    public Mono<ContactEntity> save(final SaveRequest request, final String username) {
        log.info("Got the request for saving " + request.getFirstName());
        return action.saveContact(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber(), username)
                .map(ContactResponseFactory::getContact);
    }

    public Mono<Integer> delete(final String request) {
        log.info("Deleting the contact number " + request);
        return action.deleteContact(request);
    }

    public Mono<List<ContactEntity>> getByFirstName(final String name) {
        log.info("Getting the Contact by First Name " + name);
        return action.findByFirstName(name)
                .collectList()
                .map(list -> list.stream().map(ContactResponseFactory::getContact).collect(Collectors.toList()));
    }

    public Mono<List<ContactEntity>> getByLastName(final String name) {
        log.info("Getting the Contact by Last Name " + name);
        return action.findByLastName(name)
                .collectList()
                .map(list -> list.stream().map(ContactResponseFactory::getContact).collect(Collectors.toList()));
    }

    public Mono<ContactEntity> getByEmail(final String email) {
        log.info("Getting the Contact by First Name " + email);
        return action.findByEmail(email)
                .map(ContactResponseFactory::getContact);
    }

    public Mono<String> updateByEmail(final SaveRequest request, final String name) {
        log.info("Got the request for updating by Email " + request.getEmail());
        return action.updateByEmail(request.getEmail(), request.getFirstName(), request.getLastName(), request.getPhoneNumber(), name)
                .map(result -> "Success");
    }

    public Mono<String> updateByPhoneNumber(final SaveRequest request, final String name) {
        log.info("Got the request for updating by Phone Number " + request.getPhoneNumber());
        return action.updateByPhoneNumber(request.getPhoneNumber(), request.getFirstName(), request.getLastName(), request.getEmail(), name)
                .map(result -> "Success");
    }

}
