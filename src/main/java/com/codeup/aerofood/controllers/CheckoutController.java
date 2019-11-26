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

    public OrderDetailRepository getOrderDetailDao() {
        return orderDetailDao;
    }

    public void setOrderDetailDao(OrderDetailRepository orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }

    public MenuItemRepository getMenuItemDao() {
        return menuItemDao;
    }

    public void setMenuItemDao(MenuItemRepository menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    public CheckoutController(OrderDetailRepository orderDetailDao, MenuItemRepository menuItemDao) {
        this.orderDetailDao = orderDetailDao;
        this.menuItemDao = menuItemDao;
    }


    // creates a list for order details and a list menu items to be placed
    // in order details
    private final List<OrderDetail> order_details = new ArrayList<>();
    private final List<MenuItem> menu_item = new ArrayList<>();

    // order confirmation number

    // item names
    public List<MenuItem> getMenuItemList() {
        return menu_item;
    }

    //item quantity
    public int getNumberOfItems() {
        return menu_item.size();
    }

    // Loop through orderDetails

    // subtotal
    public double getSubtotal() {
        double subtotal = MenuItem.getPrice();
        subtotal = subtotal * getNumberOfItems();
        return subtotal;
    }

    // total
    public double getTotal(double total) {
        total = getSubtotal() * 1.0825;
        return total;
    }

    // Date/Time

    // Gate number


    @GetMapping("/checkout")
    public String checkout(Model viewModel) {
        viewModel.addAttribute("menuItem", new MenuItem());
        return  "/checkout";
    }

    @GetMapping("/payment")
    public String payment(Model viewModel) {
        viewModel.addAttribute("menuItem", new MenuItem());
        return  "/payment";
    }

    @GetMapping("/order-confirmation")
    public String confirmOrder(Model viewModel) {
        viewModel.addAttribute("menuItem", new MenuItem());
        return "/order-confirmation";
    }
}
