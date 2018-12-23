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

/**
 * The Spring controller for ApplicationUser blog app.
 */
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

    /**
     * The password hashing library.
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * The home route.
     * @param p The Swing security principal.
     * @param model The Spring Model object.
     * @return The route to index.html.
     */
    @GetMapping("/")
    public String home(
                Principal p,
            Model model) {
        ApplicationUser loggedInUser = null;
        if (p != null) {
            loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        }
        model.addAttribute("loggedInUser", loggedInUser);
        return "index";
    }

    /**
     * The login route.
     * @param p The Swing security principal.
     * @param model The Spring Model object.
     * @return The route to login.html.
     */
    @GetMapping("/login")
    public String login(
            Principal p,
            Model model) {
        ApplicationUser loggedInUser = null;
        if (p != null) {
            loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        }
        model.addAttribute("loggedInUser", loggedInUser);
        return "login";
    }

    /**
     * The register route.
     * @param p The Swing security principal.
     * @param model The Spring Model object.
     * @return The route to register.html.
     */
    @GetMapping("/register")
    public String index(
            Principal p,
            Model model) {
        ApplicationUser loggedInUser = null;
        if (p != null) {
            loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        }
        model.addAttribute("loggedInUser", loggedInUser);
        return "register";
    }

    /**
     * The users route.
     * @param p The Swing security principal.
     * @param model The Spring Model object.
     * @return The route to users.html.
     */
    @GetMapping("/users")
    public String getUsers(
            Principal p,
            Model model) {
        model.addAttribute("users", userRepository.findAll());
        ApplicationUser loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        model.addAttribute("loggedInUser", loggedInUser);
        return "users";
    }

    /**
     * The user details route.
     * @param p The Swing security principal.
     * @param userId The user id.
     * @param model The Spring Model object.
     * @return
     */
    @GetMapping("/users/{userId}")
    public String getUser(
            Principal p,
            @PathVariable long userId,
            Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        //model.addAttribute("principal", p);
        ApplicationUser loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        model.addAttribute("loggedInUser", loggedInUser);
        return "userDetail";
    }

    /**
     * Route to the currently logged in users profile page.
     * @param model The Spring Model object.
     * @param p The Swing security principal.
     * @return The route to the userDetail page for the logged in user.
     */
    @GetMapping("/myProfile")
    public String getMyProfile(
            Model model,
            Principal p) {
        ApplicationUser loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        model.addAttribute("user", userRepository.findById(loggedInUser.getId()).get());
        model.addAttribute("loggedInUser", loggedInUser);
        return "userDetail";
    }

    /**
     * Adds a new application user to the Spring repository.
     * @param p The Swing security principal.
     * @param model The Spring Model object.
     * @param firstName
     * @param lastName
     * @param userName
     * @param bio
     * @param dateOfBirth
     * @return
     */
    @PostMapping("/register")
    public RedirectView create(
            Principal p,
            Model model,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String bio,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth) {
        ApplicationUser user = new ApplicationUser(firstName, lastName, userName, bCryptPasswordEncoder.encode(password), bio, dateOfBirth.toString().substring(0, 10));
        userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    /**
     *
     * @param p The Swing security principal.
     * @param userId The user id.
     * @param body The post.
     * @return Redirect to user details route.
     */
    @PostMapping("/users/{userId}/add-post")
    public RedirectView addPost(
            Principal p,
            @PathVariable long userId,
            @RequestParam String body) {
        ApplicationUser loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        ApplicationUser postingUser = userRepository.findById(userId).get();
        //TODO Refine with better pattern for error condition
        if (loggedInUser.getId() != postingUser.getId()) {
            System.out.println("Error: attempt to post to another users account rejected.");
            return new RedirectView("/users/{userId}");
        }
        Post post = new Post(body, new Date().toString().substring(0, 19), postingUser);
        postingUser.addPost(post);
        postRepository.save(post);
        userRepository.save(postingUser);
        return new RedirectView("/users/{userId}");
    }

    /**
     * A route that allows followerUserId to follow followedUserId.
     * @param p The Swing security principal.
     * @param followedUserId The user being followed.
     * @return The redirect route to the list of users page.
     */
    @PostMapping("/users/{followedUserId}/add-followed")
    public RedirectView addFollowing(
            Principal p,
            @PathVariable long followedUserId) {
        ApplicationUser loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        ApplicationUser followerUser = userRepository.findById(loggedInUser.getId()).get();
        //TODO Refine with better pattern for error condition
        ApplicationUser followedUser = userRepository.findById(followedUserId).get();
        if (loggedInUser.getId() == followedUser.getId()) {
            System.out.println("Error: attempt to follow yourself rejected.");
            return new RedirectView("/users/{userId}");
        }
        followerUser.addFollowedUser(followedUser);
        userRepository.save(followerUser);
        return new RedirectView("/feed");
    }

    /**
     * Route to get the aggregate posts of users being followed by the logged in user.
     * @param model The Spring Model object.
      * @param p The Swing security principal.
     * @return The route to the followed users feed.
     */
    @GetMapping("/feed")
    public String getFeed(
            Principal p,
            Model model) {
        ApplicationUser loggedInUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken)p).getPrincipal();
        model.addAttribute("loggedInUser", userRepository.findById(loggedInUser.getId()).get());
        return "feed";
    }
}
