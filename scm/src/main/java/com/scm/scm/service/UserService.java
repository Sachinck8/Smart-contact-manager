package com.scm.scm.service;

import com.scm.scm.entity.Role;
import com.scm.scm.entity.User;
import com.scm.scm.repository.RoleRepo;
import com.scm.scm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register new user
    public User registerUser(User user) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        Role role = roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(new HashSet<>());
        user.getRoles().add(role);

        // Save user
        return userRepository.save(user);
    }
    public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
}

public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
}

}
