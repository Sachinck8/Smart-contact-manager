package com.scm.scm.config;

import com.scm.scm.entity.Role;
import com.scm.scm.repository.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {

            if (!roleRepository.existsByName("USER")) {
                Role r = new Role();
                r.setName("USER");
                roleRepository.save(r);
            }

            if (!roleRepository.existsByName("ADMIN")) {
                Role r = new Role();
                r.setName("ADMIN");
                roleRepository.save(r);
            }

            System.out.println("Roles created");
        };
    }
}
