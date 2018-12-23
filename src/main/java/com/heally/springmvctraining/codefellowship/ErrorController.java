package com.heally.springmvctraining.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring controller for runtime errors.
 */
@Controller
public class ErrorController {

    /**
     * Error route.
     * @param model The Spring model.
     * @return The route to error.html.
     */
    @GetMapping("/error")
    public String index(Model model) {
        //TODO need to sys out a stack trace...but where is it???
        System.out.println("error");
        return "error";
    }
}
