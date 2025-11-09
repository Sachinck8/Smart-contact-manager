package com.scm.scm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.scm.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByEmailContaining(String email);
    Optional<User> findByEmail(String email);
}
