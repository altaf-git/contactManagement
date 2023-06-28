package org.contacts.management.logic.manage.DAOHelper;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.persistence.data.Contact;
import org.contacts.management.persistence.data.ContactRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
@Log
public class ContactsSaveHelper extends CommonHelper {

    private final ContactRepository contactsRepository;

    public Mono<Contact> saveContact(final String firstName, final String lastName, final String email, final String phoneNumber, final String requestedBy) {
        final LocalDateTime now = LocalDateTime.now();
        if (!checkPhoneNumber(phoneNumber)) {
            return Mono.error(new ContactsException(400, "Invalid Phone Number", "The Phone Number is invalid. Enter only 10 Digits"));
        }
        final Contact contact = new Contact(null, firstName, lastName, email, phoneNumber, now, requestedBy, null, null);
        return contactsRepository.checkContact(email, phoneNumber)
                .flatMap(i -> {
                    log.info("The number of records present with this contact "+i);
                    if (i.compareTo(0) > 0) {
                        return Mono.error(new ContactsException(400, "Already Present", "The Email or Phone Number is already is present in the database. Try Updating it"));
                    }
                    else {
                        return contactsRepository.save(contact);
                    }
                });
    }

    private Boolean checkPhoneNumber(final String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
}
