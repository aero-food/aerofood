package com.codeup.aerofood.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "redirect:/search";
    }
    @GetMapping( "/home")
    public String showHome(){
        return "home";
    }



    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
