package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.MenuCategory;
import com.codeup.aerofood.repositories.MenuCategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuCategoryController {

    private final MenuCategoryRepository menuCategoryDao;

    public MenuCategoryController(MenuCategoryRepository menuCategoryDao) {
        this.menuCategoryDao = menuCategoryDao;
    }


    @GetMapping("/menuCategory/index")
    public String showCategory(Model viewModel) {
        viewModel.addAttribute("menuCategories", menuCategoryDao.findAll());
        return "/menu_category/maintain";
    }

//    Add
    @GetMapping("/menuCategory/add")
    public String showCreate(Model vModel) {
        System.out.println("before new menu category");
        vModel.addAttribute("menuCategory", new MenuCategory());
        return "menu_category/add";
    }

    @PostMapping("/menuCategory/add")
    public String create(@ModelAttribute MenuCategory newCategory, Model viewModel) {
        System.out.println("new menu category");
        menuCategoryDao.save(newCategory);
        viewModel.addAttribute("menuCategories", menuCategoryDao.findAll());
        return "redirect:/menuCategory/index";
    }

    // Update menu category
    @GetMapping( "/menuCategory/{id}/edit")
    public String updatePost(@PathVariable long id, Model viewModel) {
        System.out.println("id = " + id);
        viewModel.addAttribute("menuCategory", menuCategoryDao.getOne(id));
        return "menu_category/edit";
    }

    @PostMapping("/menuCategory/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String description) {
        MenuCategory oldCategory = menuCategoryDao.getOne(id);
        oldCategory.setDescription(description);
        menuCategoryDao.save(oldCategory);
        return "redirect:/menuCategory/index";
    }

//    Delete
    @PostMapping("/menuCategory/{id}/delete")
    public String updateCategory(@PathVariable long id) {
        System.out.println("delete");
        System.out.println("id = " + id);
        menuCategoryDao.deleteById(id);
        return "redirect:/menuCategory/index";
    }

}
