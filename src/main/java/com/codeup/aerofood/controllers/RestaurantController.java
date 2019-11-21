package com.codeup.aerofood.controllers;

import com.codeup.aerofood.repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestaurantController {

    private RestaurantRepository restaurants;

    public RestaurantRepository getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantRepository restaurants) {
        this.restaurants = restaurants;
    }

    public RestaurantController(RestaurantRepository restaurants) {
        this.restaurants = restaurants;
    }

    @GetMapping("/search")
    public String search(Model model){

        model.addAttribute("restaurants", restaurants.findAll());

        return "search";
    }

    @GetMapping("/restaurants/{id}")
    public String show(@PathVariable long id, Model model){

        model.addAttribute("restaurants", restaurants.getOne(id));

        return  "restaurants/show";
    }


}
