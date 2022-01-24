package com.flock.TP_server.models;

import java.util.List;

public class Contacts {
    private List<Contact> contactList;

    public Contacts(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
