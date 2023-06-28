package org.contacts.management.web.manage.router;

import org.contacts.management.web.manage.handler.ContactsHandler;
import org.contacts.management.web.manage.handler.HealthCheckAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class ManagementRouter {

    public static final String SAVE = "/contacts/v1/save";
    public static final String DELETE = "/contacts/v1/delete";
    public static final String FIND_BY_FIRST_NAME = "/contacts/v1/find/firstName";
    public static final String FIND_BY_LAST_NAME = "/contacts/v1/find/lastName";
    public static final String FIND_BY_EMAIL = "/contacts/v1/find/email";
    public static final String UPDATE_BY_EMAIL = "/contacts/v1/update/byEmail";
    public static final String UPDATE_BY_PHONE_NUMBER = "/contacts/v1/update/byNumber";
    public static final String HEALTH = "/contacts/v1/echo";

    @Bean
    public RouterFunction<ServerResponse> routes(final ContactsHandler contactsHandler) {
        return RouterFunctions
                .route(POST(SAVE).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::save)
                .andRoute(DELETE(DELETE).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::delete)
                .andRoute(GET(FIND_BY_FIRST_NAME).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::findByFirstName)
                .andRoute(GET(FIND_BY_LAST_NAME).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::findByLastName)
                .andRoute(GET(FIND_BY_EMAIL).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::findByEmail)
                .andRoute(PUT(UPDATE_BY_EMAIL).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::updateByEmail)
                .andRoute(PUT(UPDATE_BY_PHONE_NUMBER).and(accept(MediaType.APPLICATION_JSON)), contactsHandler::updateByPhoneNumber)
                .andRoute(GET(HEALTH).and(accept(MediaType.APPLICATION_JSON)), HealthCheckAware::health);
    }
}
