package com.codeup.aerofood.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm() {
        //System.out.println("authentication");
        return "users/login";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return "redirect:/home";
    }
}
