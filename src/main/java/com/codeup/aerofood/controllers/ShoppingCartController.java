package com.codeup.aerofood.controllers;

import com.codeup.aerofood.repositories.OrderRepository;
import com.codeup.aerofood.services.MenuItemService;
import com.codeup.aerofood.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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


//    @GetMapping("/shoppingCart")
//    public ModelAndView shoppingCart(){
//        ModelAndView modelAndView = new ModelAndView("shoppingCart");
//        modelAndView.addObject("items", shoppingCartService.getItemsInCart());
////        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
////        modelAndView.addObject("total_stripe", shoppingCartService.getTotal().toString().replace(".", ""));
//        return modelAndView;
//    }
    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model){
//        ModelAndView modelAndView = new ModelAndView("shoppingCart");
//        modelAndView.addObject("items", shoppingCartService.getItemsInCart());
//        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
//        modelAndView.addObject("total_stripe", shoppingCartService.getTotal().toString().replace(".", ""));

        model.addAttribute("items", shoppingCartService.getItemsInCart());

        //map.entrySet().forEach(entry -> {
        //    System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        //});

//        shoppingCartService.getItemsInCart().entrySet().forEach(entry ->{
//            System.out.println(entry.getKey().getId());
////            System.out.println(entry.getKey().getTitle());
//        });

        return "shoppingCart";
    }

//    @GetMapping("/shoppingCart/addItem/{menuItemId}")
//    public ModelAndView addItem(@PathVariable("menuItemId") Long menuItemId){
//        ModelAndView modelAndView = new ModelAndView("shoppingCart");
//        return modelAndView;
//    }

//    @PostMapping("/shoppingCart/addItem/{menuItemId}")
//    public ModelAndView addItemToCart(@PathVariable("menuItemId") Long menuItemId){
//        menuItemService.findById(menuItemId).ifPresent(shoppingCartService::addItem);
//        return shoppingCart();
//    }
//
//    @PostMapping("/shoppingCart/removeProduct/{menuItemId}")
//    public ModelAndView removeItemFromCart(@PathVariable("menuItemId") Long menuItemId){
//        menuItemService.findById(menuItemId).ifPresent(shoppingCartService::removeItem);
//        return shoppingCart();
//    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView order(@RequestParam("gate") String gate){
        ModelAndView modelAndView = new ModelAndView("checkoutSuccess");
            shoppingCartService.checkout(gate);

            modelAndView.addObject("order_num", orderRepository.findAll().size());

            return modelAndView;
    }
}
