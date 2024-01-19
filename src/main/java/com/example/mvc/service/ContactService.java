package com.example.mvc.service;

import com.example.mvc.Contact;

import java.util.List;

public interface ContactService {
    public List<Contact> getAllContacts();

    public Contact addContact(Contact contact);

    public Contact findContactById(Long id);
    public Contact update(Contact contact);

    public void delete(Long id);
}
