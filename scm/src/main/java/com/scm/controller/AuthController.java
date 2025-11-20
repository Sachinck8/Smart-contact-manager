package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // login page
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // register page
    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }
}
