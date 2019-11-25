package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.Orders;
import com.codeup.aerofood.models.User;
import com.codeup.aerofood.repositories.OrderRepository;
import com.codeup.aerofood.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    private UserRepository userDao;

    private OrderRepository orderDao;

    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, OrderRepository orderDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String registerUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "sign-up";
    }

    @GetMapping("/login")
    public String loginUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/login";
    }

    @PostMapping("/login")
    public String redirectUser(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "home";
    }

    @GetMapping("/my-orders")
    public String displayMyOrders(Model viewModel) {
        //User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Orders> orders = orderDao.findAll(); //userDao.getOne(loggedUser.getId()).getPosts();
        //userDao.getOne(loggedUser.getId()).getPosts();

        //viewModel.addAttribute("restaurant", userDao.getOne(loggedUser.getId()).getBlog_description());
        viewModel.addAttribute("orders", orders);
        return "users/user-orders";
    }

    @GetMapping("/order/{id}")
    public String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("orderDetails", orderDao.getOne(id));
        return "users/order";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User newUser, Model viewModel) {
        String regexUS = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        String regexInternational = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        String regexEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        //System.out.println("newUser.getPassword() = " + newUser.getPassword());
        String hash = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hash);
        //System.out.println("newUser.getPassword() = " + newUser.getPassword());


        // Verify the phone number format

        Pattern pattern = Pattern.compile(regexUS);

        Matcher matcher = pattern.matcher(newUser.getPhone_number());

        if (!matcher.matches()) {
            pattern = Pattern.compile(regexInternational);

            matcher = pattern.matcher(newUser.getPhone_number());
            if (!matcher.matches()) {
                viewModel.addAttribute("error", "Please valid phone number");
                return "sign-up";
            }
        }

        // Verify the email format

        pattern = Pattern.compile(regexEmail);

        matcher = pattern.matcher(newUser.getEmail());

        if (!matcher.matches()) {
            viewModel.addAttribute("error", "Please provide a correct email format");
            return "sign-up";
        }

        // Check for duplicates

        List<User> userList = userDao.findAll();
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
                viewModel.addAttribute("error", "The user id exists in the system");
                return "sign-up";
            }
            if (user.getEmail().equalsIgnoreCase(newUser.getEmail())) {
                viewModel.addAttribute("error", "The email exists in the system");
                return "sign-up";
            }
            if (user.getPhone_number().equalsIgnoreCase(newUser.getPhone_number())) {
                viewModel.addAttribute("error", "The phone number exists in the system");
                return "sign-up";

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