package com.example.mvc.repository;

import com.example.mvc.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    public List<Contact> findAll();

    public Optional<Contact> findById(Long id);

    public Contact save(Contact contact);

    public Contact update(Contact contact);

    public void delete(Long id);




}
