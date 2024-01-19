package com.example.mvc.service;

import com.example.mvc.Contact;
import com.example.mvc.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{

    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Contact addContact(Contact contact){
        return contactRepository.save(contact);
    }

    public Contact findContactById(Long id){
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.update(contact);
    }

    public void delete(Long id){
        contactRepository.delete(id);
    }

}
