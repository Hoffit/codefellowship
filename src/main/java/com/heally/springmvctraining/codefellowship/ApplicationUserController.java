package com.heally.springmvctraining.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ApplicationUserController {

    /**
     * A spring repository (JPA) of users.
     */
    @Autowired
    private ApplicationUserRepository userRepository;

    /**
     * A spring repository (JPA) of posts.
     */
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }

    @GetMapping("/register")
    public String index(Model model) {
        return "register";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable long userId, Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        return "userDetail";
    }

    @PostMapping("/register")
    public RedirectView create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String bio,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth) {
        ApplicationUser user = new ApplicationUser(firstName, lastName, userName, bCryptPasswordEncoder.encode(password), bio, dateOfBirth);
        userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @PostMapping("/users/{userId}/add-post")
    public RedirectView addPost(Principal p,
                                @PathVariable long userId,
                                @RequestParam String body) {
        ApplicationUser user = userRepository.findById(userId).get();
        Post post = new Post(body, new Date());
        user.addPost(post);
        postRepository.save(post);
        userRepository.save(user);
        return new RedirectView("/users/{userId}");
    }
}
