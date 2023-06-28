package org.contacts.management.web.manage.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
