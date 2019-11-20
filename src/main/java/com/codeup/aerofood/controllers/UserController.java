package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.User;
import com.codeup.aerofood.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {
    private UserRepository userDao;


    //
//
    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }


    @GetMapping("/register")
    public String registerUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "/sign-up";
    }

    @GetMapping("/login")
    public String loginUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "/users/login";
    }

    @PostMapping("/login")
    public String redirectUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "/home";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User newUser, Model viewModel) {
        //System.out.println("newUser.getPassword() = " + newUser.getPassword());
//        String hash = passwordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(hash);
        //System.out.println("newUser.getPassword() = " + newUser.getPassword());
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty() || newUser.getPassword().isBlank()) {
            viewModel.addAttribute("error", "Please provide a password");
            return "/sign-up";
        }
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
                viewModel.addAttribute("error", "The user id exists in the system");
                return "/sign-up";
            }
            if (user.getEmail().equalsIgnoreCase(newUser.getEmail())) {
                viewModel.addAttribute("error", "The email exists in the system");
                return "/sign-up";
            }
            if (user.getPhone_number().equalsIgnoreCase(newUser.getPhone_number())) {
                viewModel.addAttribute("error", "The phone number exists in the system");
                return "/sign-up";

            }
        }

        userDao.save(newUser);
        return "redirect:login";
    }


    @GetMapping("/logout")
    public String logoutUser() {
        return "redirect:/home";
    }


}
