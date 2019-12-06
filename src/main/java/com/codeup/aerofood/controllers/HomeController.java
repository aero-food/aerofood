package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String showHome() {
//        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (loggedUser.getIsAdmin()) {
//            return "redirect:/search";
//        } else {
            return "home";
//        }
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
