package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.OrderDetail;
import com.codeup.aerofood.repositories.OrderDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

// need view
 // need controller

@Controller
public class CheckoutController {
    private OrderDetailRepository orderDetailDao;

    private final List<MenuItem> itemList = new ArrayList<>();

    public List<MenuItem> getMenuItemList() {
        return itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public double getSubtotal() {
        double subtotal = double.ZERO;

    }

    public CheckoutController(OrderDetailRepository Order)

    @GetMapping("/checkout")
    public String checkout(Model viewModel) {

        viewModel.addAttribute("menuItem", new MenuItem());
        return  ;
    }

    @PostMapping("/order-confirmation")
    public String confirmOrder(Model viewModel) {
        viewModel
    }
}
