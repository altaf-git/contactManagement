package org.contacts.management.logic.manage.DAOHelper;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.dslentities.exceptions.ClientSideException;
import org.contacts.management.dslentities.exceptions.ErrorBody;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log
public class CommonHelper {

    static class ContactsException extends ClientSideException {
        public ContactsException(int statusCode, String code, String msg) {
            super(statusCode, createErrorBody(statusCode, code, msg));
        }

        private static ErrorBody createErrorBody(int statusCode, String code, String msg) {
            ErrorBody errorBody = new ErrorBody();
            errorBody.setHttpStatus(statusCode);
            errorBody.setCode(code);
            errorBody.setMessage(msg);
            return errorBody;
        }
    }
}
