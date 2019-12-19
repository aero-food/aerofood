package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.OrderDetail;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.OrderDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


// item name
// item quantity
// item price
// subtotal
// tax = 8.25%
// total price
// delivery time
// delivery gate


@Controller
public class CheckoutController {
    private OrderDetailRepository orderDetailDao;
    private MenuItemRepository menuItemDao;

    public CheckoutController(OrderDetailRepository orderDetailDao, MenuItemRepository menuItemDao) {
        this.orderDetailDao = orderDetailDao;
        this.menuItemDao = menuItemDao;
    }


    // creates a list for order details and a list menu items to be placed
    // in order details
    private final List<OrderDetail> order_details = new ArrayList<>();
    private final List<MenuItem> menu_item = new ArrayList<>();

    // item names
    public List<MenuItem> getMenuItemList() {
        return menu_item;
    }

    //item quantity
    public int getNumberOfItems() {
        return menu_item.size();
    }

    // subtotal
//    public float getSubtotal() {
//        float subtotal = itemPrice * getNumberOfItems();
//        return subtotal;
//    }

    // total
//    public float getTotal(double total) {
//        total = getSubtotal() * 1.0825;
//        return Float.parseFloat(String.valueOf(total));
//    }

    @GetMapping("/checkout")
    public String checkout(Model viewModel) {
        viewModel.addAttribute("menuItem", new MenuItem());
        return  "orders/checkout";
    }

    @GetMapping("/payment")
    public String payment(Model viewModel) {
        viewModel.addAttribute("menuItem", new MenuItem());
        return  "orders/payment";
    }

    @GetMapping("/order-confirmation")
    public String confirmOrder(Model viewModel) {
        viewModel.addAttribute("menuItem", new MenuItem());
        return "orders/order-confirmation";
    }
}
