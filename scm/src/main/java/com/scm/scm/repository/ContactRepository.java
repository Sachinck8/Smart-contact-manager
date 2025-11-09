package com.scm.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.scm.scm.entity.Contact;
import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, String> {

    @RestResource(path = "byEmail", rel = "byEmail")
    List<Contact> findByEmail(@Param ("email") String email);
    @RestResource(path = "byPhoneNumber", rel = "byPhoneNumber")
    List<Contact> findByPhoneNumber(@Param ("phoneNumber") String phoneNumber);
    @RestResource(path = "byName", rel = "byName")
    List<Contact> findByName (@Param("name") String name);
}
