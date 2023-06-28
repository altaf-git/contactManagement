package org.contacts.management.dslentities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractBaseException extends RuntimeException {
    protected final int statusCodeToSendBackToClient;
    protected final ErrorBody errorBody;
}
