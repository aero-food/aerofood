package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Cuisine;
//import com.codeup.aerofood.repositories.CuisineRepository;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;

import com.codeup.aerofood.repositories.CuisineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CuisineController {

    private final CuisineRepository cuisineDao;

    public CuisineController(CuisineRepository cuisineDao) {
        this.cuisineDao = cuisineDao;
    }

    @GetMapping("/cuisine/index")
    public String showCuisine(Model viewModel) {
//
       viewModel.addAttribute("cuisineCategories", cuisineDao.findAll());
       return "cuisine/list";
    }

    //    Add
    @GetMapping("/cuisine/add")
    public String showCreate(Model vModel) {
//        System.out.println("before new menu category");
        vModel.addAttribute("cuisine", new Cuisine());
        return "cuisine/addCuisine";
    }

    @PostMapping("/cuisine/add")
    public String create(@ModelAttribute Cuisine newCategory, Model viewModel) {
//        System.out.println("new menu category");
        cuisineDao.save(newCategory);
        viewModel.addAttribute("cuisineCategories", cuisineDao.findAll());
        return "redirect:/cuisine/index";
    }

    // Update menu category
    @GetMapping( "/cuisine/{id}/edit")
    public String updatePost(@PathVariable long id, Model viewModel) {
        System.out.println("id = " + id);
        viewModel.addAttribute("cuisine", cuisineDao.getOne(id));
        return "cuisine/editCuisine";
    }

    @PostMapping("/cuisine/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String description) {
        Cuisine oldCategory = cuisineDao.getOne(id);
        oldCategory.setDescription(description);
        cuisineDao.save(oldCategory);
        return "redirect:/cuisine/index";
    }

    //    Delete
    @PostMapping("/cuisine/{id}/delete")
    public String updateCategory(@PathVariable long id) {
        System.out.println("delete");
        System.out.println("id = " + id);
        cuisineDao.deleteById(id);
        return "redirect:/cuisine/index";
    }
}
