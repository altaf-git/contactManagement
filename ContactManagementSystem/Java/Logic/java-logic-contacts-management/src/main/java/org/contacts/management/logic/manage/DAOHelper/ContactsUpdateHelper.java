package org.contacts.management.logic.manage.DAOHelper;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.persistence.data.ContactRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Log
public class ContactsUpdateHelper extends CommonHelper {

    private final ContactRepository contactsRepository;

    public Mono<Integer> updateByEmail(final String email, final String firstName, final String lastName, final String phoneNumber, final String requestedBy) {
        final LocalDateTime now = LocalDateTime.now();
        if (email == null || email.equals("")) {
            return Mono.error(new ContactsException(400, "Invalid Email", "Email entered is Empty"));
        }
        log.info("Updating the Contact by Email: " + email);
        return contactsRepository.updateByEmail(email, firstName, lastName, phoneNumber, requestedBy, now);
    }

    public Mono<Integer> updateByPhoneNumber(final String phoneNumber, final String firstName, final String lastName, final String email, final String requestedBy) {
        final LocalDateTime now = LocalDateTime.now();
        if (phoneNumber == null || phoneNumber.equals("")) {
            return Mono.error(new ContactsException(400, "Invalid Phone Number", "Phone Number entered is Empty"));
        }
        log.info("Updating the Contact by Phone Number: " + phoneNumber);
        return contactsRepository.updateByPhoneNumber(phoneNumber, firstName, lastName, email, requestedBy, now);
    }
}
