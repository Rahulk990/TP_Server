package com.flock.TP_server.controllers;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.services.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ContactsDataController {

    @Autowired
    private UserService contactsService;

    @GetMapping("/contacts")
    public List<Contact> getUserContacts(@RequestAttribute int userId) {
        return contactsService.getUserContacts(userId);
    }

    @PostMapping(path = "/contact", consumes="application/json", produces="application/json")
    public Contact addUserContact(@RequestAttribute int userId, @RequestBody Contact contact) {
        return contactsService.addUserData(userId, contact);
    }

    @PutMapping("/contact")
    public Contact updateUserContact(@RequestAttribute int userId, @RequestBody Contact contact) {
        return contactsService.updateUserContact(userId, contact);
    }

    @DeleteMapping("/contact/{contactId}")
    public Contact deleteUserContact(@RequestAttribute int userId, @PathVariable String contactId) {
        return contactsService.deleteUserContact(userId, contactId);
    }

}
