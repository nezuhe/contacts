package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrchowaniec.contacts.models.User;
import pl.piotrchowaniec.contacts.models.services.UserService;

@Controller
public class RegistrationController {

    private String repeatedPassword;

    final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("userData", new User());
        model.addAttribute("repeatedPassword", repeatedPassword);
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, Model model) {
        if (!passwordConfirmation(user, repeatedPassword)) {
            model.addAttribute("registrationErrorMessage", "Podane hasła są różne");
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("registrationErrorMessage", "Podany login jest już zajęty");
            return "registration";
        }
        return "login";
    }

//        if (!userData.getPassword().equals(confirmPassword)) {
//            model.addAttribute("registrationErrorMessage", "Podane hasła są róźne");
//            return "registration";
//        }
//        if (!userService.login(userData)) {
//            return "registration-complete";
//        } else {
//        }
//        return "redirect:/registration";

    private boolean passwordConfirmation(User user, String repeatedPassword) {
        if (user.getPassword().equals(repeatedPassword)) return true;
        return false;
    }
}
