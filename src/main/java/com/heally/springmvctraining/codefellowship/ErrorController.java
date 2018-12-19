package com.heally.springmvctraining.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring controller for Error.
 */
@Controller
public class ErrorController {

    @GetMapping("/error")
    public String index(Model model) {
        return "error2";
    }
}
