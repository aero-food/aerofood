package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.MenuCategory;
import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import com.codeup.aerofood.repositories.MenuItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        viewModel.addAttribute("menuItems", menuItemDao.findAll());
        return "/menu_item/maintainItem";
    }

    //    Add
    @GetMapping("/menuItem/addItem")
    public String showCreate(Model vModel) {
        vModel.addAttribute("dish_types", menuCategoryDao.findAll());
        vModel.addAttribute("menuItem", new MenuItem());
        return "menu_item/addItem";
    }

    @PostMapping("/menuItem/addItem")
    public String create(@ModelAttribute MenuItem newItem, @RequestParam Integer dish_type , Model viewModel) {
       newItem.setDish_type(dish_type);
        menuItemDao.save(newItem);
        return "redirect:/menuItem/index";
    }

    // Update menu item
    @GetMapping( "/menuItem/{id}/editItem")
    public String updatePost(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("menuItem", menuItemDao.getOne(id));
        return "menu_item/editItem";
    }

    @PostMapping("/menuItem/{id}/editItem")
    public String update(@PathVariable long id, @RequestParam String description) {
        MenuItem oldItem = menuItemDao.getOne(id);
        oldItem.setDescription(description);
        menuItemDao.save(oldItem);
        return "redirect:/menuItem/index";
    }

    //    Delete
    @PostMapping("/menuItem/{id}/delete")
    public String updateItem(@PathVariable long id) {
        System.out.println("delete");
        System.out.println("id = " + id);
        menuItemDao.deleteById(id);
        return "redirect:/menuItem/index";
    }
}
