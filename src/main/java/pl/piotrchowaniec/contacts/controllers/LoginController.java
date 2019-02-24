package pl.piotrchowaniec.contacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piotrchowaniec.contacts.models.User;
import pl.piotrchowaniec.contacts.models.services.UserService;
import pl.piotrchowaniec.contacts.models.services.UserSession;

@Controller
public class LoginController {

    final
    UserService userService;

    final
    UserSession userSession;

    @Autowired
    public LoginController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if(!userService.login(user)) {
            model.addAttribute("loginError", "Zły login lub hasło");
            return "login";
        }
        return "redirect:/home_page";
    }

    @GetMapping("/logout")
    public String logout() {
        if (userSession.isLoggedIn()) {
            userSession.setLoggedIn(false);
        }
        return "redirect:/login";
    }
}
