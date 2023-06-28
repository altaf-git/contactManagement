package org.contacts.management.dslentities.exceptions;

import java.time.Instant;

public class ServerSideException extends AbstractBaseException {
    public ServerSideException(final int statusCodeToSendBackToClient, final ErrorBody errorBody) {
        super(statusCodeToSendBackToClient, errorBody);
    }

    public ServerSideException() {
        super(500, new ErrorBody(500, "", "", Instant.now()));
    }
}