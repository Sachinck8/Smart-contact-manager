package com.scm.scm.repository;

import com.scm.scm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);  // return Optional<Role> instead of Role
}
