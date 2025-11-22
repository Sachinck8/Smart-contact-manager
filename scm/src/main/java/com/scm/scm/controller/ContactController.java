package com.scm.scm.controller;

import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;
import com.scm.scm.service.ContactService;
import com.scm.scm.service.CustomUserDetailsService;
import com.scm.scm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserRepository userRepository;

    //  Get logged-in user
    private User getLoggedUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElse(null);
    }

    //  Show All Contacts Page
    @GetMapping("/contacts")
    public String viewContacts(Model model, Authentication auth) {

        User user = getLoggedUser(auth);

        model.addAttribute("contacts", contactService.getContactsForUser(user));

        return "user/contacts"; // page: templates/user/contacts.html
    }

    //  Show Add Contact Form
    @GetMapping("/add-contact")
    public String addContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "user/add_contact"; // page: templates/user/add_contact.html
    }

    //  Save Contact
    @PostMapping("/save-contact")
    public String saveContact(@ModelAttribute Contact contact, Authentication auth) {

        User user = getLoggedUser(auth);
        contact.setUser(user);  // Assign contact to logged user

        contactService.saveContact(contact);

        return "redirect:/user/contacts?success";
    }

    //  Edit Contact Page
    @GetMapping("/edit/{id}")
    public String editContact(@PathVariable Long id, Model model) {

        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);

        return "user/edit_contact"; // page: templates/user/edit_contact.html
    }

    //  Update Cont
    @PostMapping("/update-contact")
    public String updateContact(@ModelAttribute Contact contact, Authentication auth) {

        User user = getLoggedUser(auth);
        contact.setUser(user); // important: keep the owner

        contactService.updateContact(contact);

        return "redirect:/user/contacts?updated";
    }

    // Delete Contact
    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {

        contactService.deleteContact(id);

        return "redirect:/user/contacts?deleted";
    }
}
