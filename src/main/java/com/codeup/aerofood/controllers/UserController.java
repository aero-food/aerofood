package com.codeup.aerofood.controllers;

import com.codeup.aerofood.repositories.UserRepository;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

//
 private UserRepository userDao;

    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }
    //
//    public UserController(UserRepository userDao) {
//        this.userDao = userDao;
//    }

    //
//    @GetMapping("/register")
//    public String registerUser(Model viewModel) {
//        viewModel.addAttribute("user", new User());
//        return "/sign-up";
//    }
//
//
//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User newUser) {
//        //System.out.println("newUser.getPassword() = " + newUser.getPassword());
////        String hash = passwordEncoder.encode(newUser.getPassword());
////        newUser.setPassword(hash);
//        userDao.save(newUser);
//        return "redirect:/login";
//    }
//
//
//    @GetMapping("/logout")
//    public String logoutUser() {
//        return "redirect:/home";
//    }


}
