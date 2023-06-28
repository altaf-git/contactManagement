package org.contacts.management.web.manage.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.contacts.management.dslentities.contact.ContactEntity;
import org.contacts.management.web.manage.helper.ManagementHelper;
import org.contacts.management.web.manage.model.SaveRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
@Log
public class ManagementService {

    private final ManagementHelper helper;

    @Transactional
    public Mono<ContactEntity> saveContact(final SaveRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return helper.save(request, username);
    }

    @Transactional
    public Mono<Integer> deleteContact(final String request) {
        return helper.delete(request);
    }

    public Mono<List<ContactEntity>> getContactByFirstName(final String name) {
        return helper.getByFirstName(name);
    }

    public Mono<List<ContactEntity>> getContactByLastName(final String name) {
        return helper.getByLastName(name);
    }

    public Mono<ContactEntity> getContactByEmail(final String name) {
        return helper.getByEmail(name);
    }

    @Transactional
    public Mono<String> updateByEmail(final SaveRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return helper.updateByEmail(request, username);
    }

    @Transactional
    public Mono<String> updateByPhoneNumber(final SaveRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return helper.updateByPhoneNumber(request, username);
    }
}