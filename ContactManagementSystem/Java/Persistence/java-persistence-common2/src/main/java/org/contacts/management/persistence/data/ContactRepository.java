package org.contacts.management.persistence.data;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ContactRepository extends ReactiveCrudRepository<Contact, UUID> {
    @Query("SELECT COUNT(first_name) FROM contacts WHERE email= :email OR phone_number= :phoneNumber")
    public Mono<Integer> checkContact(final String email, final String phoneNumber);
    @Query("SELECT * FROM contacts WHERE first_name= :firstName")
    public Flux<Contact> findByFirstName(final String firstName);
    @Query("SELECT * FROM contacts WHERE last_name= :lastName")
    public Flux<Contact> findByLastName(final String lastName);
    @Query("SELECT * FROM contacts WHERE email= :email")
    public Mono<Contact> findByEmail(final String email);
    @Query("DELETE FROM contacts WHERE phone_number= :phoneNumber")
    public Mono<Integer> deleteByPhoneNumber(final String phoneNumber);

    @Modifying
    @Query("UPDATE contacts SET first_name= :firstName, last_name= :lastName, phone_number= :phoneNumber, modified_by= :requestedBy, modified_on= :now WHERE email= :email")
    public Mono<Integer> updateByEmail(final String email, final String firstName, final String lastName, final String phoneNumber, final String requestedBy, final LocalDateTime now);
    @Modifying
    @Query("UPDATE contacts SET first_name= :firstName, last_name= :lastName, email= :email, modified_by= :requestedBy, modified_on= :now WHERE phone_number= :phoneNumber")
    public Mono<Integer> updateByPhoneNumber(final String phoneNumber, final String firstName, final String lastName, final String email, final String requestedBy, final LocalDateTime now);
}
