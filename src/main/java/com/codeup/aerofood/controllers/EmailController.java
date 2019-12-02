package com.codeup.aerofood.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmailController {

    @GetMapping("/sendMail/{email}")
    public String sendEmail(@PathVariable(value = "email", required = true), String email) {
        return "";
    }
}
