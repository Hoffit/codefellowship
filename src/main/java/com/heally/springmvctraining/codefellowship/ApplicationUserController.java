package com.heally.springmvctraining.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class ApplicationUserController {

    /**
     * A spring repository (JPA) of users.
     */
    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/users")
    public RedirectView create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String bio,
            @RequestParam Date dateOfBirth) {
        userRepository.save(new ApplicationUser(firstName, lastName, userName, bCryptPasswordEncoder.encode(password), bio, dateOfBirth));
        return new RedirectView("/");
    }

    @GetMapping("/register")
    public String index(Model model) {
        return "register";
    }
}
