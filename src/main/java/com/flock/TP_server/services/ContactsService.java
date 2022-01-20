package com.flock.TP_server.services;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    public List<Contact> getUserContacts(int userId) {
        return contactsRepository.getUserContacts(userId);
    }

    public boolean addUserContact(int userId, Contact contact){
        contact.setUserId(userId);
        contact.setContactId((new Timestamp(System.currentTimeMillis()).toString()));
        return contactsRepository.addUserContact(contact);
    }

    public boolean updateUserContact(Contact contact){
        return contactsRepository.updateUserContact(contact);
    }

    public boolean deleteUserContact(int userId, String contactId){
        return contactsRepository.deleteUserContact(userId, contactId);
    }

}
