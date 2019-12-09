package com.codeup.aerofood.controllers;

import com.codeup.aerofood.repositories.OrderRepository;
import com.codeup.aerofood.services.MenuItemService;
import com.codeup.aerofood.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private MenuItemService menuItemService;
    private OrderRepository orderRepository;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, MenuItemService menuItemService, OrderRepository orderRepository){
        this.shoppingCartService = shoppingCartService;
        this.menuItemService = menuItemService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model){

        model.addAttribute("items", shoppingCartService.getItemsInCart());

        model.addAttribute("total", shoppingCartService.getTotal());

        model.addAttribute("totalTax", shoppingCartService.getTotalWithTax());

        model.addAttribute("totalStripe", shoppingCartService.getTotalWithTax().toString().replace(".", ""));

        shoppingCartService.getTotal();

        return "shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public String order(@RequestParam("checkout_address") String checkout_address, Model model) {

//            shoppingCartService.checkout(checkout_address);

//        model.addAttribute("order_num", orderRepository.findAll().size());
        model.addAttribute("order_num", 1);
        model.addAttribute("gate", checkout_address);

        return "success";
    }

}
