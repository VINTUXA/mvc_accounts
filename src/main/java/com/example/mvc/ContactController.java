package com.example.mvc;

import com.example.mvc.service.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactService;



    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("contacts", contactService.getAllContacts());
        return "index";
    }

    @GetMapping("contact/create")
    public String showCreateForm(Model model){
        model.addAttribute("contact", new Contact());
        return "create";
    }

    @PostMapping("/contact/create")
    public String createContact(@ModelAttribute Contact contact){
        contact.setId(System.currentTimeMillis());
//        System.out.println(contact);
        contactService.addContact(contact);
//        contacts.add(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Contact contact = findContactById(id);
        if(contact != null){
            model.addAttribute("contact", contact);
            return "edit";
        }

        return "redirect:/";
    }

    @PostMapping("/contact/edit")
    public  String editContact(@ModelAttribute Contact contact){
        contactService.update(contact);
//        Contact existedContact = findContactById(contact.getId());
//        if(existedContact != null){
//            existedContact.setFirstName(contact.getFirstName());
//            existedContact.setLastName(contact.getLastName());
//            existedContact.setPhoneNumber(contact.getPhoneNumber());
//            existedContact.setEmail(contact.getEmail());
//        }
        return "redirect:/";
    }

    private Contact findContactById(Long id){
//        return contacts.stream()
//                .filter(c -> c.getId().equals(id))
//                .findFirst()
//                .orElse(null);
        return contactService.findContactById(id);
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        Contact contact = findContactById(id);
        if ( contact != null){
            contactService.delete(contact.getId());
        }
        return "redirect:/";
    }


}
