package com.scm.scm.service;

import com.scm.scm.entity.User;
import com.scm.scm.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

        
            public UserService(UserRepository userRepository) {
                    this.userRepository = userRepository;
                        }

                            // ✅ Load user by username (used by Spring Security)
                                @Override
                                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                                            // Find user by email or username
                                                    User user = userRepository.findByEmail(username)
                                                                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

                                                                            // Return the User object (it must implement UserDetails)
                                                                                    return user;
                                                                                        }
                                                                                        }