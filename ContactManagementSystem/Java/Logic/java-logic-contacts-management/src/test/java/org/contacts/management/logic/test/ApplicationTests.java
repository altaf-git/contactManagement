package org.contacts.management.logic.test;

import org.contacts.management.logic.manage.service.ContactsService;
import lombok.extern.java.Log;
import org.contacts.management.persistence.data.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@EnableAutoConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = "org.contacts")
@ConfigurationPropertiesScan(basePackages = {"org.contacts"})
@ActiveProfiles("test")
@Log
class ApplicationTests {

    static {
        System.setProperty("io.netty.native.workdir", "/opt/Applications/netty");
    }

    @Autowired
    private ContactsService service;


    @Test
    void testContactsSaveService() {
        Contact contact = service.saveContact("Mohd", "Altaf", "moaltaf042@gmail.com", "9140513285", "Myself").log().block();
        assert contact != null;
        log.info(contact.toString());
    }
    @Test
    void testContactsDeleteService() {
        Integer n = service.deleteContact("9140513285").log().block();
        assert n == null;
    }
    @Test
    void testContactsFindService() {
        Contact contact = service.findByEmail("moaltaf042@gmail.com").log().block();
        assert contact != null;
        log.info(contact.toString());
    }
    @Test
    void testContactsUpdateService() {
        service.updateByPhoneNumber("9140513285", "mohd","altaf","moaltaf0423@gmail.com","Myself").log().block();
    }
}