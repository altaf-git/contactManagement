package org.contacts.management.logic.manage.service;


import org.contacts.management.logic.manage.DAOHelper.ContactsDeleteHelper;
import org.contacts.management.logic.manage.DAOHelper.ContactsFindHelper;
import org.contacts.management.logic.manage.DAOHelper.ContactsSaveHelper;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.logic.manage.DAOHelper.ContactsUpdateHelper;
import org.contacts.management.persistence.data.Contact;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Log
public class ContactsService {

    private final ContactsSaveHelper saveHelper;
    private final ContactsFindHelper findHelper;
    private final ContactsDeleteHelper deleteHelper;
    private final ContactsUpdateHelper updateHelper;

    @Transactional
    public Mono<Contact> saveContact(final String firstName, final String lastName, final String email, final String phoneNumber, final String requestedBy) {
        return saveHelper.saveContact(firstName.toLowerCase(), lastName.toLowerCase(), email.toLowerCase(), phoneNumber, requestedBy.toLowerCase());
    }

    public Flux<Contact> findByFirstName(final String firstName) {
        return findHelper.findByFirstName(firstName.toLowerCase());
    }

    public Flux<Contact> findByLastName(final String lastName) {
        return findHelper.findByLastName(lastName.toLowerCase());
    }

    public Mono<Contact> findByEmail(final String email) {
        return findHelper.findByEmail(email.toLowerCase());
    }
    @Transactional
    public Mono<Integer> deleteContact(final String phoneNumber) {
        return deleteHelper.deleteContact(phoneNumber);
    }
    public Mono<Integer> updateByEmail(final String email, final String firstName, final String lastName, final String phoneNumber, final String requestedBy) {
        return updateHelper.updateByEmail(email.toLowerCase(), firstName.toLowerCase(), lastName.toLowerCase(), phoneNumber, requestedBy.toLowerCase());
    }
    public Mono<Integer> updateByPhoneNumber(final String phoneNumber, final String firstName, final String lastName, final String email, final String requestedBy) {
        return updateHelper.updateByPhoneNumber(phoneNumber, firstName.toLowerCase(), lastName.toLowerCase(), email.toLowerCase(), requestedBy.toLowerCase());
    }
}
