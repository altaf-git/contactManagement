package org.contacts.management.persistence.data;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("contacts")
public record Contact(@Id @Column("uuid") UUID uuid,
                      @Column("first_name") String firstName,
                      @Column("last_name") String lastName,
                      @Column("email") String email,
                      @Column("phone_number") String phoneNumber,
                      @CreatedDate @Column("created_on") LocalDateTime createdOn,
                      @CreatedBy @Column("created_by") String createdBy,
                      @Column("modified_on") LocalDateTime modifiedOn,
                      @Column("modified_by") String modifiedBy){

                    }
