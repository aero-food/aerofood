package com.codeup.aerofood.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestaurantController {



    @GetMapping("/search")
    public String search(){
        return "search";
    }


}
