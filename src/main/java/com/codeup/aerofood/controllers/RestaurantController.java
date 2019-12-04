package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Cuisine;
import com.codeup.aerofood.models.MenuCategory;
import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Restaurant;
import com.codeup.aerofood.repositories.CuisineRepository;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.RestaurantRepository;
import com.codeup.aerofood.services.MenuItemService;
import com.codeup.aerofood.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class RestaurantController {

    private RestaurantRepository restaurantDao;

    private CuisineRepository cuisineDao;

    private MenuCategoryRepository menuCategoryDao;

    private MenuItemRepository menuItemsDao;

    private ShoppingCartService shoppingCartService;

    private MenuItemService menuItemService;



    public RestaurantController(RestaurantRepository restaurantDao,
                                CuisineRepository cuisineDao,
                                MenuCategoryRepository menuCategoryDao,
                                MenuItemRepository menuItemsDao,
                                ShoppingCartService shoppingCartService,
                                MenuItemService menuItemService) {
        this.restaurantDao = restaurantDao;
        this.cuisineDao = cuisineDao;
        this.menuCategoryDao = menuCategoryDao;
        this.menuItemsDao = menuItemsDao;
        this.shoppingCartService = shoppingCartService;
        this.menuItemService = menuItemService;
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

        model.addAttribute("cart", shoppingCartService.getItemsInCart());

        return "show";
    }

//    PostMapping use pathvariable to got back to restaurant and requestParam for the menuItemID//


    @PostMapping("/restaurants/{id}/{menuItemId}")
    public String addShow(@PathVariable long id, @PathVariable long menuItemId, Model model) {

//        menuItemService.findById(menuItemId).ifPresent(shoppingCartService::addItem);

        shoppingCartService.addItem(menuItemsDao.getOne(menuItemId));

        return "redirect:/restaurants/{id}";
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
    public String updatePost(@PathVariable long id,
                             Model viewModel) {
        viewModel.addAttribute("restaurant", restaurantDao.getOne(id));
        // Cuisine type
        int index;
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
        List<String> itemMenuTitles = new ArrayList<>();
        List<String> newItemMenuCategories = new ArrayList<>();
        for (MenuItem currentMenuItem : restaurantMenuItemList) {
            itemMenuTitles.add(currentMenuItem.getTitle());
            if (!newItemMenuCategories.contains(currentMenuItem.getMenuCategory().getDescription())) {
                newItemMenuCategories.add(currentMenuItem.getMenuCategory().getDescription());
            }
        }
        List<MenuItem> newMenuItemList;
        if (itemMenuTitles.size() > 0) {
            newMenuItemList = menuItemsDao.findMenuItemByRestaurantIsNullAndTitleIsNotIn(itemMenuTitles);
        } else {
            newMenuItemList = menuItemsDao.findAll();
        }

        List<MenuItem> currentMenuItems = restaurantDao.getOne(id).getMenu_items();
        Collections.sort(currentMenuItems, MenuItem.MenuCategoryComparator);
        List<MenuItem> sortedCurrentList = new ArrayList<>();
        for(MenuItem element: currentMenuItems){
            sortedCurrentList.add(element);
        }
        viewModel.addAttribute("menuItems", sortedCurrentList);

        Collections.sort(newMenuItemList, MenuItem.MenuCategoryComparator);
        List<MenuItem> sortedList = new ArrayList<>();
        for(MenuItem element: newMenuItemList){
            sortedList.add(element);
        }
        viewModel.addAttribute("newMenuItemList",  sortedList);
        viewModel.addAttribute("newMenuItemListCategories", newItemMenuCategories);

        viewModel.addAttribute("itemList", restaurantDao.getOne(id).getCuisines());
        viewModel.addAttribute("dish_types", menuCategoryDao.findAll());
        viewModel.addAttribute("restaurantId", id);
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
                         @RequestParam(value = "cuisines", required = false) int[] cuisines,
                         @RequestParam(value = "selectedMenuItems", required = false) MenuItem[] menuItems) {
        Restaurant oldRestaurant = restaurantDao.getOne(id);
        oldRestaurant.setAirport(airport);
        oldRestaurant.setGate(gate);
        oldRestaurant.setName(name);
        oldRestaurant.setPhone_number(phone_number);
        oldRestaurant.setPicture_url(picture_url);
        oldRestaurant.setThumbnail(thumbnail);
        addRestaurantCuisine(cuisines, oldRestaurant);
        addRestaurantMenuItems(menuItems, oldRestaurant);
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
        System.out.println("add menu items to restaurant");
        //Restaurant oldRestaurant = restaurantDao.getOne(id);
        //addRestaurantMenuItems(menuItems, oldRestaurant);
        //restaurantDao.save(oldRestaurant);
//        menuItemsDao.save(oldItem);
        addRestaurantMenuItem(id, restaurantId.get(0));
        Restaurant oldRestaurant = restaurantDao.getOne(restaurantId.get(0));
        return "redirect:/restaurant/" + oldRestaurant.getId() + "/edit";
    }

    // Update restaurant menu item

    @PostMapping("/restaurant/{id}/editRestaurantMenuItem")
    public String update(@PathVariable long id,
                         @RequestParam List<Long> menuItemId,
                         @RequestParam List<String> description,
                         @RequestParam List<Float> price,
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
