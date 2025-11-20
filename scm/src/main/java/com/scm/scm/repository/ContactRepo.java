package com.scm.scm.repository;

import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(User user); // Fetch contacts for the logged-in user
}
