package org.contacts.management.dslentities.exceptions;

import java.time.Instant;

public class ClientSideException extends AbstractBaseException {
    public ClientSideException(final int statusCodeToSendBackToClient, final ErrorBody errorBody) {
        super(statusCodeToSendBackToClient, errorBody);
    }

    public ClientSideException(final int statusCodeToSendBackToClient) {
        super(statusCodeToSendBackToClient, new ErrorBody(statusCodeToSendBackToClient, "", "", Instant.now()));
    }
}
