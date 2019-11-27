package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    public MenuItem findMenuItemByTitleAndRestaurant(String title, Restaurant restaurant);
    public List<MenuItem> findMenuItemByRestaurantIsNull();
//    public List<MenuItem> findMenuItemByRestaurantIsNullOrderByMenuCategory(Restaurant restaurant);
}
