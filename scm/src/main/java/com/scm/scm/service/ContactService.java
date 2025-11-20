package com.scm.scm.service;

import com.scm.scm.entity.Contact;
import com.scm.scm.entity.User;
import com.scm.scm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getContactsForUser(User user) {
        return contactRepository.findByUser(user);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
    public Contact getContactById(Long id) {
    return contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
}

}
