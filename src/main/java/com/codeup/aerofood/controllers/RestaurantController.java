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

import java.math.BigDecimal;
import java.util.*;

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
        Set<Cuisine> newCuisineList = new HashSet<>();
        Set<Cuisine> oldCuisine = currentRestaurant.getCuisines();
        Set<Restaurant> oldRestaurant;
        Cuisine currentCuisine;

        // Remove the restaurant from the cuisine list
        for (Cuisine cuisine : currentRestaurant.getCuisines()) {
            //System.out.println("cuisine.getDescription() = " + cuisine.getDescription());
            oldRestaurant = cuisineDao.getOne(cuisine.getId()).getRestaurants();
            oldRestaurant.remove(cuisine);
            cuisine.setRestaurants(oldRestaurant);
            cuisineDao.save(cuisine);
        }

        if (cuisines != null) {
            for (int i = 0; i < cuisines.length; i++) {
                currentCuisine = cuisineDao.getOne((long) cuisines[i]);
                newCuisineList.add(currentCuisine);
                oldRestaurant = cuisineDao.getOne(currentCuisine.getId()).getRestaurants();
                currentCuisine.setRestaurants(oldRestaurant);
                cuisineDao.save(currentCuisine);
            }
        }

        currentRestaurant.setCuisines(newCuisineList);
        restaurantDao.save(currentRestaurant);

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
        return;
    }

    private void addRestaurantMenuItems(MenuItem[] menuItems, Restaurant currentRestaurant) {
        // Get the categories
        MenuItem newMenuItem;
        List<MenuItem> newMenuItemList = new ArrayList<>();
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
            newMenuItemList.add(newMenuItem);
        }
        currentRestaurant.setMenu_items(newMenuItemList);
        return;
    }

    @GetMapping("/search")
    public String search(Model model) {

        model.addAttribute("page_name", "Search Restaurants");
        model.addAttribute("restaurants", restaurantDao.findAllByDeletedEquals(0));
        return "search";
    }

    @GetMapping("/preview")
    public String preview(Model model) {
        model.addAttribute("page_name", "Preview");
        model.addAttribute("restaurants", restaurantDao.findAllByDeletedEquals(0));
        return "search";
    }

    @GetMapping("/restaurants/{id}")
    public String show(@PathVariable long id, Model model) {

        model.addAttribute("restaurants", restaurantDao.getOne(id));
        model.addAttribute("menu", restaurantDao.getOne(id).getMenu_items());
        model.addAttribute("cart", shoppingCartService.getItemsInCart());
        return "show";
    }

    @PostMapping("/restaurants/{id}/{menuItemId}")
    public String addShow(@PathVariable long id, @PathVariable long menuItemId, Model model) {

        menuItemService.findById(menuItemId).ifPresent(shoppingCartService::addItem);
        return "redirect:/restaurants/{id}";
    }


    @GetMapping("/restaurant/index")
    public String showCuisine(Model viewModel) {

        viewModel.addAttribute("restaurants", restaurantDao.findAllByDeletedEquals(0));
        return "restaurant/listRestaurants";
    }

    //    Add
    @GetMapping("/restaurant/add")
    public String showCreate(Model vModel) {
        vModel.addAttribute("restaurant", new Restaurant());
        vModel.addAttribute("dish_types", cuisineDao.findAll());

        List<MenuItem> currentMenuItems = menuItemsDao.findMenuItemByRestaurantIsNull();
        Collections.sort(currentMenuItems, MenuItem.MenuCategoryComparator);
        List<MenuItem> sortedCurrentList = new ArrayList<>();
        for (MenuItem element : currentMenuItems) {
            sortedCurrentList.add(element);
        }
        vModel.addAttribute("menuItems", sortedCurrentList);

        return "restaurant/addRestaurant";
    }

    @PostMapping("/restaurant/add")
    public String create(@ModelAttribute Restaurant newRestaurant,
                         @RequestParam(value = "dish_types", required = false) int[] dish_types,
                         @RequestParam(value = "selectedMenuItems", required = false) MenuItem[] menuItems,
                         Model viewModel) {

        Boolean dishTypesExists = false;
        Boolean menuItemsExists = false;
        if (dish_types != null) {
            dishTypesExists = true;
            addRestaurantCuisine(dish_types, newRestaurant);
            restaurantDao.save(newRestaurant);
        }
        if (menuItems != null) {
            menuItemsExists = true;
            addRestaurantMenuItems(menuItems, newRestaurant);
            restaurantDao.save(newRestaurant);
        }
        if (!dishTypesExists && !menuItemsExists) {
            restaurantDao.save(newRestaurant);
        }
        return "redirect:/restaurant/index";
    }

    // Update restaurant
    @GetMapping("/restaurant/{id}/edit")
    public String updateRestaurant(@PathVariable long id,
                                   Model viewModel) {
        viewModel.addAttribute("restaurant", restaurantDao.getOne(id));
        // Cuisine type
        int index;
        Set<Cuisine> cuisineList = restaurantDao.getOne(id).getCuisines();
        if (!cuisineList.isEmpty()) {
            Set<Cuisine> restaurantCuisine = restaurantDao.getOne(id).getCuisines();
            List<Cuisine> availableCategories = cuisineDao.findAll();
            for (Cuisine cuisineType : restaurantCuisine) {
                index = availableCategories.indexOf(cuisineType);
                if (index >= 0) {
                    //System.out.println("remove");
                    availableCategories.remove(index);
                }
            }
//
            for (Cuisine cuisine : restaurantCuisine) {
                System.out.println("cuisine.getDescription() = " + cuisine.getDescription());
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
        if (cuisines != null) {
            System.out.println("cuisines");
            System.out.println("cuisines = " + cuisines);
            addRestaurantCuisine(cuisines, oldRestaurant);
            restaurantDao.save(oldRestaurant);
        }
        if (menuItems != null) {
            addRestaurantMenuItems(menuItems, oldRestaurant);
        }
        restaurantDao.save(oldRestaurant);
        return "redirect:/restaurant/index";
    }

    //    Delete Restaurant
    @PostMapping("/restaurant/{id}/delete")
    public String deleteRestaurant(@PathVariable long id) {
        System.out.println("delete id = " + id);
        Restaurant currentRestaurant = restaurantDao.getOne(id);
        Set<Restaurant> oldRestaurant;
        // Remove the restaurant from the cuisine list
        for (Cuisine cuisine : currentRestaurant.getCuisines()) {
            //System.out.println("cuisine.getDescription() = " + cuisine.getDescription());
            oldRestaurant = cuisineDao.getOne(cuisine.getId()).getRestaurants();
            oldRestaurant.remove(cuisine);
            cuisine.setRestaurants(oldRestaurant);
            cuisineDao.save(cuisine);
        }
//        for (Cuisine cuisine : currentRestaurant.getCuisines()) {
//            //System.out.println("cuisine.getDescription() = " + cuisine.getDescription());
//            currentRestaurant.de
//        }
//        restaurantDao.getOne(id).setCuisines(null);
//        restaurantDao.save(currentRestaurant);

        currentRestaurant.setDeleted(1);
        restaurantDao.save(currentRestaurant);
        //restaurantDao.deleteById(id);
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

        MenuItem selectedMenuItem = menuItemsDao.getOne(id);
        Restaurant restaurant = selectedMenuItem.getRestaurant();
        menuItemsDao.deleteById(id);
        return "redirect:/restaurant/" + restaurant.getId() + "/edit";
    }
}
