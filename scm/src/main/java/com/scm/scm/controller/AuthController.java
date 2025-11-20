package com.scm.scm.controller;

import com.scm.scm.model.User;
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

    // Login page
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login - Smart Contact Manager");
        model.addAttribute("content", "auth/login :: content");
        return "layout/base";
    }

    // Register page
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Register - Smart Contact Manager");
        model.addAttribute("content", "auth/register :: content");
        model.addAttribute("user", new User()); // form binding
        return "layout/base";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already registered!");
            model.addAttribute("pageTitle", "Register - Smart Contact Manager");
            model.addAttribute("content", "auth/register :: content");
            return "layout/base";
        }

        userService.registerUser(user); // save user
        model.addAttribute("success", "Registration successful! You can login now.");
        model.addAttribute("pageTitle", "Register - Smart Contact Manager");
        model.addAttribute("content", "auth/register :: content");
        model.addAttribute("user", new User());
        return "layout/base";
    }
}
