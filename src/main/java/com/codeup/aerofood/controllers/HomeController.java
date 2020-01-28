package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Airport;
import com.codeup.aerofood.models.User;
import com.codeup.aerofood.repositories.AirportRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    AirportRepository airportDao;

    public HomeController(AirportRepository airportDao) {
        this.airportDao = airportDao;
    }

    @GetMapping("/")
    public String index(Model viewModel) {
        viewModel.addAttribute("airports", airportDao.findAll());
        return "home";
    }

    @GetMapping("/home")
    public String showHome( Model viewModel) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.getIsAdmin()) {
            return "redirect:/search";
        } else {
//            Display restaurant choices
            viewModel.addAttribute("airports", airportDao.findAll());
            return "home";
        }
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
