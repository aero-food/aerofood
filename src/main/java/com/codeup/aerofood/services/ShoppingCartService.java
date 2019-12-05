package com.codeup.aerofood.services;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Orders;
import com.codeup.aerofood.models.User;
import com.codeup.aerofood.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {

    private RestaurantRepository restaurantDao;

    private MenuItemRepository menuItemsDao;

    private UserRepository userDao;

    private Map<MenuItem, Integer> orderItems = new HashMap<>();

    private OrderRepository ordersDao;

    @Autowired
    public ShoppingCartService(RestaurantRepository restaurantDao, MenuItemRepository menuItemsDao, UserRepository userDao, OrderRepository orderRepository){
        this.restaurantDao = restaurantDao;
        this.menuItemsDao = menuItemsDao;
        this.userDao = userDao;
        this.ordersDao = ordersDao;
    }

    public void addItem(MenuItem menuItem){
        if(orderItems.containsKey(menuItem)){
            orderItems.replace(menuItem, orderItems.get(menuItem) + 1);
        } else {
            orderItems.put(menuItem, 1);
        }
    }

    public void removeItem(MenuItem menuItem){
        if (orderItems.containsKey(menuItem)){
            if(orderItems.get(menuItem) > 1)
                orderItems.replace(menuItem, orderItems.get(menuItem) - 1);
            if(orderItems.get(menuItem) == 1){
                orderItems.remove(menuItem);
            }
        }
    }

    public Map<MenuItem, Integer> getItemsInCart(){
        return Collections.unmodifiableMap(orderItems);
    }


    public BigDecimal getTotal() {

        double total = 0;

        for (MenuItem key : orderItems.keySet()) {
//            total += Double.valueOf(key.getPrice());
            total += Double.valueOf((key.getPrice().toString()));

        }

        System.out.println(total);

        BigDecimal finalPrice= new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);

        return finalPrice;
    }
    public BigDecimal getTotalWithTax() {

        double total = 0;

        for (MenuItem key : orderItems.keySet()) {
//            total += Double.valueOf(key.getPrice());
            total += Double.valueOf((key.getPrice().toString()));

        }

        System.out.println(total);

        total = (total * .0825) + total;

        BigDecimal finalPrice= new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);

        return finalPrice;
    }

    public void checkout(String gate) {

        newOrder(gate);
    }

    public void newOrder(String gate) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Float orderTotal = Float.valueOf(getTotalWithTax().toString());

        Orders order = new Orders();

        order.setGate(gate);
        order.setTotal(orderTotal);
        order.setUser(sessionUser);


        ordersDao.save(order);
    }

}
