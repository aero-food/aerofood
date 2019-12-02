package com.codeup.aerofood.controllers;

import com.codeup.aerofood.services.EmailSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailSvc emailService;

    @GetMapping("/sendMail/{email}")
    public void sendEmail(@PathVariable String email) {
        emailService.prepareAndSend(email,"Test", "Test text for testing.");
    }
}
