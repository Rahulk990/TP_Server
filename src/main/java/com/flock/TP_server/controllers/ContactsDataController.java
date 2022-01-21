package com.flock.TP_server.controllers;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.services.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Contact> getUserContacts(@RequestAttribute Integer userId) {
        return contactsService.getUserContacts(userId);
    }

    @PostMapping(path = "/contact", consumes="application/json", produces="application/json")
    public ResponseEntity<Contact> addUserContact(@RequestAttribute Integer userId, @RequestBody Contact contact) {
        Contact contactCreated =  contactsService.addUserContact(userId, contact);
        return new ResponseEntity<>(contactCreated, HttpStatus.CREATED);
    }

    @PutMapping("/contact")
    public Contact updateUserContact(@RequestBody Contact contact) {
        Contact contactUpdated = contactsService.updateUserContact(contact);
        return contactUpdated;
    }

    @DeleteMapping("/contact/{contactId}")
    public boolean deleteUserContact(@RequestAttribute Integer userId, @PathVariable String contactId) {
        return contactsService.deleteUserContact(userId, contactId);
    }

}
