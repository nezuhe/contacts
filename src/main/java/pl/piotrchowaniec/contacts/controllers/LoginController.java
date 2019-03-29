package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrchowaniec.contacts.models.forms.LoginForm;
import pl.piotrchowaniec.contacts.models.services.UserService;
import pl.piotrchowaniec.contacts.models.services.UserSession;

@Controller
public class LoginController {

    private final UserService userService;
    private final UserSession userSession;

    @Autowired
    public LoginController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        Model model) {
        if(!userService.login(loginForm)) {
            model.addAttribute("message", "Zły login lub hasło");
            return "login";
        }
        return "redirect:/home_page";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        if (userSession.isLoggedIn()) {
            model.addAttribute("message", "Wylogowano pomyślnie");
            userSession.setLoggedIn(false);
        }
        return "login";
    }
}
