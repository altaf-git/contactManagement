package org.contacts.management.web.manage.factory;

import lombok.extern.java.Log;
import org.contacts.management.dslentities.contact.ContactEntity;
import org.contacts.management.persistence.data.Contact;

@Log
public class ContactResponseFactory {
    private ContactResponseFactory() {
    }

    public static ContactEntity getContact(final Contact contact) {
        final ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName(contact.firstName());
        contactEntity.setLastName(contact.lastName());
        contactEntity.setEmail(contact.email());
        contactEntity.setPhoneNumber(contact.phoneNumber());
        return contactEntity;
    }
}
