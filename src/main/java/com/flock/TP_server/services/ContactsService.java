package com.flock.TP_server.services;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.repositories.ContactsRepository;
import com.flock.TP_server.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    @Autowired
    private TransactionService transactionService;

    public List<Contact> getUserContacts(Integer userId) {
        return contactsRepository.getUserContacts(userId);
    }

    public Contact addUserContact(Integer userId, Contact contact){
        contact.setUserId(userId);
        contact.setContactId(StringUtils.generateRandomString(10));
        contact.setScore(0);

        Contact contact1 = contactsRepository.addUserContact(contact);
        transactionService.insertTransaction(userId, contact1);
        return contact1;
    }

    public Contact updateUserContact(Contact contact){
        Contact contact1 = contactsRepository.updateUserContact(contact);
        transactionService.insertTransaction(contact.getUserId(), contact1);
        return contact1;
    }

    public void deleteUserContact(Integer userId, String contactId){
        contactsRepository.deleteUserContact(userId, contactId);
        transactionService.insertTransaction(userId, new Contact(contactId, true));
    }

}
