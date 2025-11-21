package com.scm.scm.controller;

import com.scm.scm.entity.User;
import com.scm.scm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // Register page
   
}
