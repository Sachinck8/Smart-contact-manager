package com.scm.scm.service;

import com.scm.scm.entity.Role;
import com.scm.scm.entity.User;
import com.scm.scm.repository.RoleRepository;
import com.scm.scm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // assign ROLE_USER
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        user.getRoles().add(role);

        // save user
        return userRepository.save(user);
    }
}
