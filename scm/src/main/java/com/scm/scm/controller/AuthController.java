package com.scm.scm.controller;

import com.scm.scm.entity.User;
import com.scm.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login"; // login.html
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register"; // register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userService.saveUser(user);

        return "redirect:/login?success";
    }
}
