package com.codeup.aerofood.controllers;


import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.OrderDetailRepository;
import org.springframework.stereotype.Controller;

@Controller
public class OrderConfirmController {
    private final MenuItemRepository menuItemDao;
    private final OrderDetailRepository orderDetailDao;

    public OrderConfirmController(MenuItemRepository menuItemDao,
                                  OrderDetailRepository orderDetailDao) {

        this.menuItemDao = menuItemDao;
        this.orderDetailDao = orderDetailDao;
    }


}
