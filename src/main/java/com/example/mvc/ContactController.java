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
        contactService.addContact(contact);
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
        return "redirect:/";
    }

    private Contact findContactById(Long id){
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
