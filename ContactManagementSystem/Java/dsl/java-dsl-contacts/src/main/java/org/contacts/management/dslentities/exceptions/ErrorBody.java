package org.contacts.management.dslentities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int httpStatus;
    private String code;
    private String message;
    private Instant timeStamp = Instant.now();
}