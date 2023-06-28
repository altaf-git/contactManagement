package org.contacts.management.logic.manage.DAOHelper;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.persistence.data.ContactRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Log
public class ContactsDeleteHelper extends CommonHelper {

    private final ContactRepository contactsRepository;

    public Mono<Integer> deleteContact(final String phoneNumber) {
        if (phoneNumber == null || phoneNumber.equals("")) {
            return Mono.error(new ContactsException(400, "Invalid Phone Number Name", "Phone Number entered is Empty"));
        }
        log.info("Deleting the contact from database: " + phoneNumber);
        return contactsRepository.deleteByPhoneNumber(phoneNumber);
    }
}
