package com.flock.TP_server.controllers;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.models.Contacts;
import com.flock.TP_server.services.ContactsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
public class ContactsDataController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/contacts")
    public Contacts getUserContacts(@RequestAttribute Integer userId) {
        return new Contacts(contactsService.getUserContacts(userId));
    }

    @PostMapping(path = "/contact", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Contact> addUserContact(@RequestAttribute Integer userId, @RequestBody Contact contact) {
        Contact contactCreated = contactsService.addUserContact(userId, contact);
        return new ResponseEntity<>(contactCreated, HttpStatus.CREATED);
    }

    @PutMapping("/contact")
    public Contact updateUserContact(@RequestBody Contact contact) {
        return contactsService.updateUserContact(contact);
    }

    @DeleteMapping("/contact/{contactId}")
    public HttpStatus deleteUserContact(@RequestAttribute Integer userId, @PathVariable @Size(min = 10, max = 10, message = "Invalid Contact Id") String contactId) {
        contactsService.deleteUserContact(userId, contactId);
        return HttpStatus.OK;
    }

}
