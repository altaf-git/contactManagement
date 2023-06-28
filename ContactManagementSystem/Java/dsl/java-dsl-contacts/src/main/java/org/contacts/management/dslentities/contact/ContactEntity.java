package org.contacts.management.dslentities.contact;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
