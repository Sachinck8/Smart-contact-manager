package com.scm.scm.controller;

import com.scm.scm.entity.User;
import com.scm.scm.repository.UserRepository;
import com.scm.scm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
   @GetMapping("/register")
   public String register(){
    return "auth/register";
   }

   @PostMapping("/register")
   public String registerUser(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password){
    
    
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);

    UserRepository.save(user);
     return "redirect:/login"; 

     }

     @GetMapping("/welcome")
     public String welcome(){
        return "layout/base";
     }

}
