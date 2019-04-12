package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrchowaniec.contacts.models.forms.ContactForm;
import pl.piotrchowaniec.contacts.models.services.ContactService;
import pl.piotrchowaniec.contacts.models.services.UserSession;

import javax.validation.Valid;

@Controller
public class ContactController {

    private final UserSession userSession;
    private final ContactService contactService;

    @Autowired
    public ContactController(UserSession userSession, ContactService contactService) {
        this.userSession = userSession;
        this.contactService = contactService;
    }

    @GetMapping("/contact/add")
    public String addContact(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact_form";
    }

    @PostMapping("/contact/add")
    public String addContact(@ModelAttribute @Valid ContactForm contactForm,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "contact_form";
        }
        if (!contactService.addContact(contactForm)) {
            model.addAttribute("contactFormMessage", "Kontakt z podanym numerem już istnieje");
            return "contact_form";
        }
        model.addAttribute("homePageMessage", "Kontakt został utworzony!");
        return "home_page";
    }

    @GetMapping("/contact/delete/{id}")
    public String contactDelete(@PathVariable("id") int id,
                                Model model) {
        contactService.deleteContact(id);
        model.addAttribute("contactsMessage", "Kontakt został usunięty!");
        return "contacts";
    }
}
