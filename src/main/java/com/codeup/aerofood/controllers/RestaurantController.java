package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Restaurant;
import com.codeup.aerofood.repositories.CuisineRepository;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestaurantController {

    private RestaurantRepository restaurants;

    private CuisineRepository cuisine;

    private MenuCategoryRepository menuCategory;

    private MenuItemRepository menuItems;


    public RestaurantController(RestaurantRepository restaurants, CuisineRepository cuisine, MenuCategoryRepository menuCategory, MenuItemRepository menuItems) {
        this.restaurants = restaurants;
        this.cuisine = cuisine;
        this.menuCategory = menuCategory;
        this.menuItems = menuItems;
    }

    public RestaurantRepository getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantRepository restaurants) {
        this.restaurants = restaurants;
    }

    public CuisineRepository getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineRepository cuisine) {
        this.cuisine = cuisine;
    }

    public MenuCategoryRepository getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategoryRepository menuCategory) {
        this.menuCategory = menuCategory;
    }

    public MenuItemRepository getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItemRepository menuItems) {
        this.menuItems = menuItems;
    }

    @GetMapping("/search")
    public String search(Model model){

        model.addAttribute("restaurants", restaurants.findAll());

        return "search";
    }

    @GetMapping("/restaurants/{id}")
    public String show(@PathVariable long id, Model model){

        model.addAttribute("restaurants", restaurants.getOne(id));

        model.addAttribute("menu", menuItems.findAll());


        return  "/show";
    }


}
