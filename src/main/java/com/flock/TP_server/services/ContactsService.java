package com.flock.TP_server.services;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contact> getUserContacts(int userId) {
        return contactsRepository.getUserContacts(userId);
    }

    public Contact addUserContact(Contact contact){
        return contactsRepository.addUserContact(contact);
    }

    public Contact updateUserContact(Contact contact){
        return contactsRepository.updateUserContact(contact);
    }

    public Contact deleteUserContact(Contact contact){
        return contactsRepository.deleteUserContact(contact);
    }

}
