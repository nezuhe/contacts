package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrchowaniec.contacts.models.forms.LoginForm;
import pl.piotrchowaniec.contacts.models.forms.RegistrationForm;
import pl.piotrchowaniec.contacts.models.services.UserService;
import pl.piotrchowaniec.contacts.models.validators.RegistrationValidator;
//import pl.piotrchowaniec.contacts.models.validators.RegistrationValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final RegistrationValidator registrationValidator;

    @Autowired
    public RegistrationController(UserService userService, RegistrationValidator registrationValidator) {
        this.userService = userService;
        this.registrationValidator = registrationValidator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationValidator);
    }

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Valid RegistrationForm registrationForm,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!userService.isLoginFree(registrationForm.getLogin())) {
            model.addAttribute("message", "Podany login jest już zajęty");
            return "registration";
        }
        userService.addUser(registrationForm);
        return "redirect:/";
    }
}
