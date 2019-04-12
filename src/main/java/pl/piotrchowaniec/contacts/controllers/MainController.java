package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.piotrchowaniec.contacts.models.services.ContactService;
import pl.piotrchowaniec.contacts.models.services.UserSession;

@Controller
public class MainController {

//    private UserSession userSession;
    private final ContactService contactService;

    @Autowired
    public MainController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("contactList", contactService.getContacts());
        return "contacts";
    }

    @GetMapping("/home_page")
    public String homePage() {
        return "home_page";
    }
}