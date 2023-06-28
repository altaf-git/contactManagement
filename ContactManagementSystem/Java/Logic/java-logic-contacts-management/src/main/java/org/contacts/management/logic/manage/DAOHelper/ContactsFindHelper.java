package org.contacts.management.logic.manage.DAOHelper;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.persistence.data.Contact;
import org.contacts.management.persistence.data.ContactRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Log
public class ContactsFindHelper extends CommonHelper {

    private final ContactRepository contactsRepository;

    public Flux<Contact> findByFirstName(final String firstName) {
        if (firstName == null || firstName.equals("")) {
            return Flux.error(new ContactsException(400, "Invalid First Name", "First Name entered is Empty"));
        }
        log.info("Finding the contact by First Name: " + firstName);
        return contactsRepository.findByFirstName(firstName);
    }

    public Flux<Contact> findByLastName(final String lastName) {
        if (lastName == null || lastName.equals("")) {
            return Flux.error(new ContactsException(400, "Invalid Last Name", "Last Name entered is Empty"));
        }
        log.info("Finding the contact by Last Name: " + lastName);
        return contactsRepository.findByLastName(lastName);
    }

    public Mono<Contact> findByEmail(final String email) {
        if (email == null || email.equals("")) {
            return Mono.error(new ContactsException(400, "Invalid Email", "Email entered is Empty"));
        }
        log.info("Finding the contact by Email: " + email);
        return contactsRepository.findByEmail(email);
    }
}
