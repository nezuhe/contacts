package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.piotrchowaniec.contacts.models.services.ContactService;
import pl.piotrchowaniec.contacts.models.services.UserSession;

@Controller
public class MainController {

    private UserSession userSession;
    private ContactService contactService;

    @Autowired
    public MainController(UserSession userSession, ContactService contactService) {
        this.userSession = userSession;
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
    public String homePage(Model model) {

        model.addAttribute("accountStatus", "Account status: " + userSession.getUserEntity().getAccountStatus());
        return "home_page";
    }
}