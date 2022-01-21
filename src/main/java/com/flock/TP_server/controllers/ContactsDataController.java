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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactsDataController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/contacts")
    public List<Contact> getUserContacts(@RequestAttribute int userId) {
        return contactsService.getUserContacts(userId);
    }

    @PostMapping(path = "/contact", consumes = "application/json", produces = "application/json")
    public Contact addUserContact(@RequestAttribute int userId, @RequestBody Contact contact) {
        return contactsService.addUserContact(userId, contact);
    }

    @PutMapping("/contact")
    public Contact updateUserContact(@RequestBody Contact contact) {
        return contactsService.updateUserContact(contact);
    }

    @DeleteMapping("/contact/{contactId}")
    public boolean deleteUserContact(@RequestAttribute int userId, @PathVariable String contactId) {
        return contactsService.deleteUserContact(userId, contactId);
    }

}
