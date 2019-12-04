package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Cuisine;
import com.codeup.aerofood.models.MenuCategory;
import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Restaurant;
import com.codeup.aerofood.repositories.CuisineRepository;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
                cuisineDao.getOne((long) cuisines[i]).setRestaurant(currentRestaurant);
                cuisineDao.save(cuisineDao.getOne((long) cuisines[i]));
            }
        }
        return;
    }

    private void addRestaurantMenuItem(long menuItemId, long restaurantId) {
        // Get the categories
        MenuItem currentMenuItem = menuItemsDao.getOne(menuItemId);
        Restaurant currentRestaurant = restaurantDao.getOne(restaurantId);

        MenuItem newMenuItem = new MenuItem(
                currentMenuItem.getTitle(),
                currentMenuItem.getDescription(),
                currentMenuItem.getPrice(),
                currentRestaurant
        );

        newMenuItem.setRestaurant(currentRestaurant);
        newMenuItem.setMenuCategory(currentMenuItem.getMenuCategory());
        menuItemsDao.save(newMenuItem);
        //
        //}
        return;
    }

    private void addRestaurantMenuItems(MenuItem[] menuItems, Restaurant currentRestaurant) {
        // Get the categories
        MenuItem newMenuItem;
        List<MenuItem> newMenuItemList = new ArrayList<>();
//        System.out.println("menuItems.length = " + menuItems.length);
        //if (menuItems != null) {

        for (int i = 0; i < menuItems.length; i++) {
            newMenuItem = new MenuItem(
                    menuItems[i].getTitle(),
                    menuItems[i].getDescription(),
                    menuItems[i].getPrice(),
                    currentRestaurant
            );
            newMenuItem.setRestaurant(currentRestaurant);
            newMenuItem.setMenuCategory(menuItems[i].getMenuCategory());
            menuItemsDao.save(newMenuItem);
            System.out.println("newMenuItem.getTitle() = " + newMenuItem.getTitle());

            newMenuItemList.add(newMenuItem);
//m
        }
        currentRestaurant.setMenu_items(newMenuItemList);
        //}
        return;
    }

    @GetMapping("/search")
    public String search(Model model) {

        model.addAttribute("restaurants", restaurantDao.findAll());

        return "search";
    }

    @GetMapping("/restaurants/{id}")
    public String show(@PathVariable long id, Model model) {


        model.addAttribute("restaurants", restaurantDao.getOne(id));
        model.addAttribute("menu", restaurantDao.getOne(id).getMenu_items());
        return "show";
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
        vModel.addAttribute("dish_types", cuisineDao.findAll());

        List<MenuItem> currentMenuItems = menuItemsDao.findMenuItemByRestaurantIsNull();
        Collections.sort(currentMenuItems, MenuItem.MenuCategoryComparator);
        List<MenuItem> sortedCurrentList = new ArrayList<>();
        for(MenuItem element: currentMenuItems){
            sortedCurrentList.add(element);
        }
        vModel.addAttribute("menuItems", sortedCurrentList);
//        vModel.addAttribute("menuItems", menuItemsDao.findMenuItemByRestaurantIsNull());

        return "restaurant/addRestaurant";
    }

    @PostMapping("/restaurant/add")
    public String create(@ModelAttribute Restaurant newRestaurant,
                         @RequestParam(value = "dish_types", required = false) int[] dish_types,
                         @RequestParam(value = "selectedMenuItems", required = false) MenuItem[] menuItems,
                         Model viewModel) {
        addRestaurantCuisine(dish_types, newRestaurant);
        restaurantDao.save(newRestaurant);
        //System.out.println("menuItems = " + menuItems);
        addRestaurantMenuItems(menuItems, newRestaurant);
        // addRestaurantId_MenuItems(newRestaurant);
        restaurantDao.save(newRestaurant);
//        viewModel.addAttribute("cuisineCategories", restaurantDao.findAll());
        return "redirect:/restaurant/index";
    }

    // Update restaurant
    @GetMapping("/restaurant/{id}/edit")
    public String updateRestaurant(@PathVariable long id,
                             Model viewModel) {
        viewModel.addAttribute("restaurant", restaurantDao.getOne(id));
        // Cuisine type
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

        // New menu items
        List<MenuItem> restaurantMenuItemList = restaurantDao.getOne(id).getMenu_items();
        List<MenuItem> newMenuItemList = menuItemsDao.findMenuItemByRestaurantIsNull(); //menuItemsDao.findAll();
        List<MenuItem> selectThisMenuItem = new ArrayList<>();
        boolean exists = false;

        for (MenuItem currentMenuItem : newMenuItemList) {
//            System.out.println("currentMenuItem.getTitle() = " + currentMenuItem.getTitle());
            for (int jIndex = 0; jIndex < restaurantMenuItemList.size(); jIndex++) {
                if (currentMenuItem.getTitle().equalsIgnoreCase(restaurantMenuItemList.get(jIndex).getTitle())) {
                    exists = true;
                }
            }
            if (!(exists)) {
                selectThisMenuItem.add(currentMenuItem);
            }
            exists = false;
        }

        viewModel.addAttribute("menuItems", restaurantDao.getOne(id).getMenu_items());
        viewModel.addAttribute("newMenuItemList", selectThisMenuItem);

        viewModel.addAttribute("itemList", restaurantDao.getOne(id).getCuisines());
        viewModel.addAttribute("dish_types", menuCategoryDao.findAll());
        viewModel.addAttribute("restaurantId", id);
        return "restaurant/editRestaurant";}

    @PostMapping("/restaurant/{id}/edit")
    public String update(@PathVariable long id,
                         @RequestParam String airport,
                         @RequestParam String gate,
                         @RequestParam String name,
                         @RequestParam String phone_number,
                         @RequestParam String picture_url,
                         @RequestParam String thumbnail,
                         @RequestParam(value = "cuisines", required = false) int[] cuisines,
                         @RequestParam(value = "selectedMenuItems", required = false) MenuItem[] menuItems) {
        Restaurant oldRestaurant = restaurantDao.getOne(id);
        oldRestaurant.setAirport(airport);
        oldRestaurant.setGate(gate);
        oldRestaurant.setName(name);
        oldRestaurant.setPhone_number(phone_number);
        oldRestaurant.setPicture_url(picture_url);
        oldRestaurant.setThumbnail(thumbnail);
        if (cuisines != null){
            addRestaurantCuisine(cuisines, oldRestaurant);
        }
       if(menuItems != null){
           addRestaurantMenuItems(menuItems, oldRestaurant);
       }
        restaurantDao.save(oldRestaurant);
        return "redirect:/restaurant/index";
    }

    //    Delete Restaurant
    @PostMapping("/restaurant/{id}/delete")
    public String deleteRestaurant(@PathVariable long id) {
//        System.out.println("delete");
//        System.out.println("id = " + id);
        restaurantDao.deleteById(id);
        return "redirect:/restaurant/index";
    }

    @PostMapping("/restaurant/{id}/addRestaurantMenuItem")
    public String update(@PathVariable long id,
                         @RequestParam List<Long> restaurantId) {
        addRestaurantMenuItem(id, restaurantId.get(0));
        Restaurant oldRestaurant = restaurantDao.getOne(restaurantId.get(0));
        return "redirect:/restaurant/" + oldRestaurant.getId() + "/edit";
    }

    // Update restaurant menu item

    @PostMapping("/restaurant/{id}/editRestaurantMenuItem")
    public String update(@PathVariable long id,
                         @RequestParam List<Long> menuItemId,
                         @RequestParam List<String> description,
                         @RequestParam List<BigDecimal> price,
                         @RequestParam List<MenuCategory> menu_category,
                         @RequestParam List<String> title) {
        System.out.println("update");
        MenuItem oldItem = menuItemsDao.getOne(id);
        Restaurant restaurant = oldItem.getRestaurant();
        int index = 0;
        for (index = 0; index < menuItemId.size(); index++) {
            if (menuItemId.get(index) == id) {
                break;
            }

        }
        oldItem.setMenuCategory(menu_category.get(index));
        oldItem.setDescription(description.get(index));
        oldItem.setPrice(price.get(index));
        oldItem.setTitle(title.get(index));
        menuItemsDao.save(oldItem);
        return "redirect:/restaurant/" + restaurant.getId() + "/edit";
    }

    //    Delete Restaurant menu item
    @PostMapping("/restaurant/{id}/deleteRestaurantMenuItem")
    public String deleteMenuItem(@PathVariable long id) {
        System.out.println("delete");
        System.out.println("id = " + id);

        MenuItem selectedMenuItem = menuItemsDao.getOne(id);
        Restaurant restaurant = selectedMenuItem.getRestaurant();

//        selectedMenuItem.
        menuItemsDao.deleteById(id);
        //return "redirect:/restaurant/index";
        return "redirect:/restaurant/" + restaurant.getId() + "/edit";
    }
}
