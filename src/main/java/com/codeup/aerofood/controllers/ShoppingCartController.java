package com.codeup.aerofood.controllers;

import com.codeup.aerofood.models.*;
import com.codeup.aerofood.repositories.MenuItemRepository;
import com.codeup.aerofood.repositories.OrderDetailRepository;
import com.codeup.aerofood.repositories.OrderRepository;
import com.codeup.aerofood.repositories.RestaurantRepository;
import com.codeup.aerofood.services.MenuItemService;
import com.codeup.aerofood.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private MenuItemService menuItemService;
    private OrderRepository orderRepository;
    private List<OrderDetail> orderDetailList = new ArrayList<>();
    private OrderDetail orderDetail;
    private Orders currentOrder;
    private Restaurant restaurant;
    private RestaurantRepository restaurantDao;
    private MenuItemRepository menuItemDao;
    private MenuItem menuItem;
    private OrderDetailRepository orderDetailDao;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MenuItemService menuItemService,
                                  OrderRepository orderRepository,
                                  RestaurantRepository restaurantDao,
                                  MenuItemRepository menuItemDao,
                                  OrderDetailRepository orderDetailDao) {
        this.shoppingCartService = shoppingCartService;
        this.menuItemService = menuItemService;
        this.orderRepository = orderRepository;
        this.restaurantDao = restaurantDao;
        this.menuItemDao = menuItemDao;
        this.orderDetailDao = orderDetailDao;
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        OrderDetail orderDetail;
//        Orders currentOrder;

        model.addAttribute("items", shoppingCartService.getItemsInCart());

        model.addAttribute("total", shoppingCartService.getTotal());

        model.addAttribute("totalTax", shoppingCartService.getTotalWithTax());

        model.addAttribute("totalStripe", shoppingCartService.getTotalWithTax().toString().replace(".", ""));

        shoppingCartService.getTotal();


        return "shoppingCart";
    }

    @GetMapping("/shoppingCart/checkout")
    public String order(@RequestParam("checkout_address") String checkout_address,
                        Model model) {
        // Add items to the orders
        System.out.println("checkout");
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Date deliveryDate = new Date(new Date().getTime() + minutes*60000);
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingTenMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));

        orderDetailList = new ArrayList<>();
        shoppingCartService.getItemsInCart().forEach((k, v) -> {
//            System.out.println("Item : " + k + " Count : " + v);
//            System.out.println("k.getId() = " + k.getId());
//            System.out.println("price = " + k.getPrice());
//            System.out.println("price = " + k.getDescription());
            //restaurant = restaurantDao.findRestaurantsByMenu_itemsEquals(k.getId());
            //restaurant = restaurantDao.findDistinctByMenu_itemsEquals(menuItemDao.getOne(k.getId()));
            menuItem = menuItemDao.getOne(k.getId());
            restaurant = menuItem.getRestaurant();
            orderDetail = new OrderDetail(k.getDescription(), k.getPrice());
            orderDetailList.add(orderDetail);
            if ("E".equals(k)) {
                System.out.println("Hello E");
            }
        });

        System.out.println("restaurant.getId() = " + restaurant.getId());
        OrderStatus currentOrderStatus = new OrderStatus();
        BigDecimal tax = shoppingCartService.getTotalWithTax().add(shoppingCartService.getTotal().negate());
        currentOrder = new Orders(checkout_address,
                afterAddingTenMins,
                new Date(),
                shoppingCartService.getTotalWithTax(),
                tax,
                shoppingCartService.getTotal(),
                loggedUser);

        currentOrder.setRestaurant(restaurant);
        currentOrder.setOrderDetails(orderDetailList);
        currentOrder.setOrderStatus(1);
        orderRepository.save(currentOrder);
        for (OrderDetail orderDetails: orderDetailList){
            orderDetails.setOrders(currentOrder);
            orderDetailDao.save( orderDetails);
        }

        model.addAttribute("order_num", currentOrder.getId());
        model.addAttribute("gate", checkout_address);
        return "success";
    }

}


