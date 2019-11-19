package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.User;
import com.codeup.aerofood.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

//
 private UserRepository userDao;

    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }


    @GetMapping("/register")
    public String registerUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "/sign-up";
    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User newUser) {
        //System.out.println("newUser.getPassword() = " + newUser.getPassword());
//        String hash = passwordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(hash);
        userDao.save(newUser);
        return "redirect:/login";
    }


    @GetMapping("/logout")
    public String logoutUser() {
        return "redirect:/home";
    }


}
