package com.scm.scm.repository;

import com.scm.scm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // For login
    boolean existsByEmail(String email); // To check if email is already registered
}
