package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Cuisine;
import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Restaurant;
import com.codeup.aerofood.repositories.CuisineRepository;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantController {

    private RestaurantRepository restaurantDao;

    private CuisineRepository cuisineDao;

    private MenuCategoryRepository menuCategoryDao;

    private MenuItemRepository menuItemsDao;


    public RestaurantController(RestaurantRepository restaurantDao,
                                CuisineRepository cuisineDao,
                                MenuCategoryRepository menuCategoryDao,
                                MenuItemRepository menuItemsDao) {
        this.restaurantDao = restaurantDao;
        this.cuisineDao = cuisineDao;
        this.menuCategoryDao = menuCategoryDao;
        this.menuItemsDao = menuItemsDao;
    }

    public RestaurantRepository getRestaurantDao() {
        return restaurantDao;
    }

    public void setRestaurantDao(RestaurantRepository restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public CuisineRepository getCuisineDao() {
        return cuisineDao;
    }

    public void setCuisineDao(CuisineRepository cuisineDao) {
        this.cuisineDao = cuisineDao;
    }

    public MenuCategoryRepository getMenuCategoryDao() {
        return menuCategoryDao;
    }

    public void setMenuCategoryDao(MenuCategoryRepository menuCategoryDao) {
        this.menuCategoryDao = menuCategoryDao;
    }

    public MenuItemRepository getMenuItemsDao() {
        return menuItemsDao;
    }

    public void setMenuItemsDao(MenuItemRepository menuItemsDao) {
        this.menuItemsDao = menuItemsDao;
    }

    private void addRestaurantCuisine(int[] cuisines, Restaurant currentRestaurant) {
        // Get the categories
        Cuisine cuisineType;
        if (cuisines != null) {
            List<Cuisine> newCuisine = new ArrayList<>();

            for (int i = 0; i < cuisines.length; i++) {
                cuisineType = cuisineDao.getOne((long) cuisines[i]);
                newCuisine.add(cuisineType);
//                System.out.println("cuisineType.getDescription() = " + cuisineType.getDescription());
            }
            currentRestaurant.setCuisines(newCuisine);
        }
        return;
    }

    @GetMapping("/search")
    public String search(Model model){

        model.addAttribute("restaurants", restaurantDao.findAll());

        return "search";
    }

    @GetMapping("/restaurants/{id}")
    public String show(@PathVariable long id, Model model){



        model.addAttribute("restaurants", restaurantDao.getOne(id));


        List<MenuItem> menuList = menuItemsDao.findAll();

        List<MenuItem> sortedList = new ArrayList<>();

        for (MenuItem menuItemList : menuList){

            if (id == menuItemList.getRestaurant().getId()){
                sortedList.add(menuItemList);
            }

        }

        model.addAttribute("menu", sortedList);

        return  "show";
    }

    @GetMapping("/restaurant/index")
    public String showCuisine(Model viewModel) {
//
        viewModel.addAttribute("restaurants", restaurantDao.findAll());
        return "/restaurant/listRestaurants";
    }

    //    Add
    @GetMapping("/restaurant/add")
    public String showCreate(Model vModel) {
        vModel.addAttribute("restaurant", new Restaurant());
        vModel.addAttribute("cuisines", cuisineDao.findAll());
        return "restaurant/addRestaurant";
    }

    @PostMapping("/restaurant/add")
    public String create(@ModelAttribute Restaurant newRestaurant,  @RequestParam(value = "cuisines", required = false) int[] cuisines, Model viewModel) {
        addRestaurantCuisine(cuisines, newRestaurant );
        restaurantDao.save(newRestaurant);
//        viewModel.addAttribute("cuisineCategories", restaurantDao.findAll());
        return "redirect:/restaurant/index";
    }

    // Update restaurant
    @GetMapping( "/restaurant/{id}/edit")
    public String updatePost(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("restaurant", restaurantDao.getOne(id));

        int index = 0;
        List<Cuisine> cuisineList = restaurantDao.getOne(id).getCuisines();
        if (!cuisineList.isEmpty()) {
            List<Cuisine> restaurantCuisine = restaurantDao.getOne(id).getCuisines();
            List<Cuisine> availableCategories = cuisineDao.findAll();
            for (int i = 0; i < restaurantCuisine.size(); i++) {
                index = availableCategories.indexOf(restaurantCuisine.get(i));
                if (index >= 0) {
                    availableCategories.remove(index);
                }
            }
            viewModel.addAttribute("cuisineList", restaurantCuisine);
            viewModel.addAttribute("newCuisines", availableCategories);
        } else {
            viewModel.addAttribute("cuisineList", cuisineDao.findAll());
        }
        viewModel.addAttribute("itemList", restaurantDao.getOne(id).getCuisines());
        return "restaurant/editRestaurant";
    }
    @PostMapping("/restaurant/{id}/edit")
    public String update(@PathVariable long id,
                         @RequestParam String airport,
                         @RequestParam String gate,
                         @RequestParam String name,
                         @RequestParam String phone_number,
                         @RequestParam String picture_url,
                         @RequestParam String thumbnail,
                         @RequestParam(value = "cuisines", required = false) int[] cuisines) {
        Restaurant oldRestaurant = restaurantDao.getOne(id);
        oldRestaurant.setAirport(airport);
        oldRestaurant.setGate(gate);
        oldRestaurant.setName(name);
        oldRestaurant.setPhone_number(phone_number);
        oldRestaurant.setPicture_url(picture_url);
        oldRestaurant.setThumbnail(thumbnail);
        addRestaurantCuisine(cuisines, oldRestaurant );
        restaurantDao.save(oldRestaurant);
        return "redirect:/restaurant/index";
    }

    //    Delete
    @PostMapping("/restaurant/{id}/delete")
    public String updateCategory(@PathVariable long id) {
//        System.out.println("delete");
//        System.out.println("id = " + id);
        restaurantDao.deleteById(id);
        return "redirect:/restaurant/index";
    }
}
