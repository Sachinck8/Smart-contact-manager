package com.scm.scm.controller;

import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;
import com.scm.scm.service.ContactService;
import com.scm.scm.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ContactService contactService;

    public UserController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    // Dashboard page
    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("pageTitle", "Dashboard - Smart Contact Manager");
        model.addAttribute("user", user);
        model.addAttribute("contacts", contactService.getContactsForUser(user));
        model.addAttribute("content", "user/dashboard :: content");
        return "layout/base";
    }

    // Add contact page
    @GetMapping("/contacts/add")
    public String addContactPage(Model model) {
        model.addAttribute("pageTitle", "Add Contact - Smart Contact Manager");
        model.addAttribute("content", "user/add-contact :: content");
        model.addAttribute("contact", new Contact());
        return "layout/base";
    }

    // Handle add contact
    @PostMapping("/contacts/add")
    public String addContact(@ModelAttribute("contact") Contact contact,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        contact.setUser(user);
        contactService.saveContact(contact);
        return "redirect:/user/dashboard";
    }

    // Edit contact page
    @GetMapping("/contacts/edit/{id}")
    public String editContactPage(@PathVariable("id") Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("pageTitle", "Edit Contact - Smart Contact Manager");
        model.addAttribute("contact", contact);
        model.addAttribute("content", "user/edit-contact :: content");
        return "layout/base";
    }

    // Handle edit contact
    @PostMapping("/contacts/edit")
    public String editContact(@ModelAttribute("contact") Contact contact,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        contact.setUser(user);
        contactService.saveContact(contact);
        return "redirect:/user/dashboard";
    }

    // Delete contact
    @GetMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
        return "redirect:/user/dashboard";
    }
}
