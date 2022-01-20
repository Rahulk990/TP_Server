package com.flock.TP_server.services;

import com.flock.TP_server.models.Contact;
import com.flock.TP_server.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

}
