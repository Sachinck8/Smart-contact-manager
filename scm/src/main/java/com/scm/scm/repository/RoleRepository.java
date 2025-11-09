package com.scm.scm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName (@Param("name") String name);
}
