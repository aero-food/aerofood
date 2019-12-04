package com.codeup.aerofood.services;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Orders;
import com.codeup.aerofood.models.User;
import com.codeup.aerofood.repositories.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
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

    @Autowired
    public ShoppingCartService(RestaurantRepository restaurantDao, MenuItemRepository menuItemsDao, UserRepository userDao){
        this.restaurantDao = restaurantDao;
        this.menuItemsDao = menuItemsDao;
        this.userDao = userDao;
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

    public void checkout(String address) {
        MenuItem menuItem;

//        menuItemsDao.save(orderItems.keySet()); //////////////////////////////////
        menuItemsDao.flush();
        orderItems.clear();

        }


//    public void newOrder(String address) {
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String timeStamp = new SimpleDateFormat("MM/dd/yyyy" + "\n" + "HH:mm:ss").format(new Date());
//        Order order[] = {
//                //NEED TO GRAB THE temp_address from the shoppingCart.html input field, google autofill works
//                new Order(timeStamp, address, orderStatusRepo.findStatusOrderPlaced(), userRepo.findById(sessionUser.getId()))
//        };
//        orderRepo.save(Arrays.asList(order));
//    }


////////////////////////////// Need to figure out
//    public void newOrder(String gate){
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String timeStamp = new SimpleDateFormat("MM/dd/yyyy" + "\n" + "HH:mm:ss").format(new Date());
//
//        Orders order[] ={
//                new Orders()
//        };
//    }


//    public BigDecimal getTotal() {
//        return orderItems.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
//    }
//
    public BigDecimal getTotal() {
        return BigDecimal.valueOf(1.99);
    }
}
