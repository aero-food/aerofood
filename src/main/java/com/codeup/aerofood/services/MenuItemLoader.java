package com.codeup.aerofood.services;


import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.RestaurantRepository;

import java.util.List;

public class MenuItemLoader {

    private RestaurantRepository restaurants;

    private MenuItemRepository menuItems;

    public MenuItemLoader(RestaurantRepository restaurants, MenuItemRepository menuItems) {
        this.restaurants = restaurants;
        this.menuItems = menuItems;
    }

//    public List<MenuItem> findMenuByRestaurant(long id){
//
//
//    }
}
