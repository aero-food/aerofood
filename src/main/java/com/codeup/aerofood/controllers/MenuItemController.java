package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.MenuCategory;
import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import com.codeup.aerofood.repositories.MenuItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuItemController {
    private final MenuItemRepository menuItemDao;
    private final MenuCategoryRepository menuCategoryDao;

    public MenuItemController(MenuItemRepository menuItemDao, MenuCategoryRepository menuCategoryDao) {
        this.menuItemDao = menuItemDao;
        this.menuCategoryDao = menuCategoryDao;
    }

    @GetMapping("/menuItem/index")
    public String showItem(Model viewModel) {
        viewModel.addAttribute("menuItems", menuItemDao.findMenuItemByRestaurantIsNull());
        return "/menu_item/maintainItem";
    }

    //    Add menu item

    @GetMapping("/menuItem/addItem")
    public String showCreate(Model vModel) {
        vModel.addAttribute("dish_types", menuCategoryDao.findAll());
        vModel.addAttribute("menuItem", new MenuItem());
        return "menu_item/addItem";
    }

    @PostMapping("/menuItem/addItem")
    public String create(@ModelAttribute MenuItem newItem,
                         @RequestParam MenuCategory menu_category,
                         Model viewModel) {
        newItem.setMenuCategory(menu_category);
        menuItemDao.save(newItem);
        return "redirect:/menuItem/index";
    }

    // Update menu item
    @GetMapping("/menuItem/{id}/editItem")
    public String updatePost(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("menuItem", menuItemDao.getOne(id));
        viewModel.addAttribute("dish_types", menuCategoryDao.findAll());
        return "menu_item/editItem";
    }

    @PostMapping("/menuItem/{id}/editItem")
    public String update(@PathVariable long id, @RequestParam String description,
                         @RequestParam Float price,
                         @RequestParam MenuCategory menu_category,
                         @RequestParam String picture_url,
                         @RequestParam String title) {
        MenuItem oldItem = menuItemDao.getOne(id);
        //System.out.println("**** menu_category = " + menu_category.getDescription());
        oldItem.setMenuCategory(menu_category);
        oldItem.setDescription(description);
        oldItem.setPrice(price);
        oldItem.setTitle(title);
        menuItemDao.save(oldItem);
        return "redirect:/menuItem/index";
    }

    // Delete
    @PostMapping("/menuItem/{id}/deleteItem")
    public String updateItem(@PathVariable long id) {
//        System.out.println("delete");
//        System.out.println("id = " + id);
        menuItemDao.deleteById(id);
        return "redirect:/menuItem/index";
    }
}
