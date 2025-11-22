package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "auth/dashboard";   // ⭐ load from /templates/user/dashboard.html
    }
}
