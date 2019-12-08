package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    public List<Restaurant> findAllByDeletedEquals(int value);
//    public MenuItem findMenuItemByTitleAndRestaurant(String title, Restaurant restaurant);
//    public List<Restaurant> findDistinctByMenu_itemsEquals(MenuItem menuItem);
//    public List<MenuItem> findMenuItemByRestaurantIsNull();
}
