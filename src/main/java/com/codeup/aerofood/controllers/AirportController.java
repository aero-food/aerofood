package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Airport;
import com.codeup.aerofood.models.Cuisine;
import com.codeup.aerofood.repositories.AirportRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AirportController {

    private final AirportRepository airportDao;

    public AirportController(AirportRepository airportDao) {
        this.airportDao = airportDao;
    }

    @GetMapping("/airport/index")
    public String showCuisine(Model viewModel) {
//
       viewModel.addAttribute("airports", airportDao.findAll());
       return "airport/listAirports";
    }

    //    Add
    @GetMapping("/airport/add")
    public String showCreate(Model vModel) {
//        System.out.println("before new menu category");
        vModel.addAttribute("airport", new Airport());
        return "airport/addAirport";
    }

    @PostMapping("/airport/add")
    public String create(@ModelAttribute Airport newAirport, Model viewModel) {
//        System.out.println("new menu category");
        airportDao.save(newAirport);
        viewModel.addAttribute("airportCategories", airportDao.findAll());
        return "redirect:/airport/index";
    }

    // Update menu category
    @GetMapping( "/airport/{id}/edit")
    public String updatePost(@PathVariable long id, Model viewModel) {
        System.out.println("id = " + id);
        viewModel.addAttribute("airport", airportDao.getOne(id));
        return "airport/editAirport";
    }

    @PostMapping("/airport/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String name) {
        Airport oldAirport = airportDao.getOne(id);
        oldAirport.setName(name);
        airportDao.save(oldAirport);
        return "redirect:/airport/index";
    }

    //    Delete
    @PostMapping("/airport/{id}/delete")
    public String updateCategory(@PathVariable long id) {
        System.out.println("delete");
        System.out.println("id = " + id);
        Airport oldAirport = airportDao.getOne(id);
        oldAirport.setDeleted(1);
        airportDao.save(oldAirport);
        return "redirect:/airport/index";
    }
}
