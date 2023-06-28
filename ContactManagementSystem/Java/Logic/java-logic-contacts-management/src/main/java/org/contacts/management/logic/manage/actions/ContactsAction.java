package org.contacts.management.logic.manage.actions;

import org.contacts.management.logic.manage.service.ContactsService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.persistence.data.Contact;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log
@Component
@AllArgsConstructor
public class ContactsAction {
    private final ContactsService contactsService;
    public Mono<Contact> saveContact(final String firstName, final String lastName, final String email, final String phoneNumber, final String requestedBy) {
        return contactsService.saveContact(firstName, lastName, email, phoneNumber, requestedBy);
    }

    public Flux<Contact> findByFirstName(final String firstName) {
        return contactsService.findByFirstName(firstName);
    }

    public Flux<Contact> findByLastName(final String lastName) {
        return contactsService.findByLastName(lastName);
    }

    public Mono<Contact> findByEmail(final String email) {
        return contactsService.findByEmail(email);
    }

    public Mono<Integer> deleteContact(final String phoneNumber) {
        return contactsService.deleteContact(phoneNumber);
    }
    public Mono<Integer> updateByEmail(final String email, final String firstName, final String lastName, final String phoneNumber, final String requestedBy) {
        return contactsService.updateByEmail(email, firstName, lastName, phoneNumber, requestedBy);
    }
    public Mono<Integer> updateByPhoneNumber(final String phoneNumber, final String firstName, final String lastName, final String email, final String requestedBy) {
        return contactsService.updateByPhoneNumber(phoneNumber, firstName, lastName, email, requestedBy);
    }
}