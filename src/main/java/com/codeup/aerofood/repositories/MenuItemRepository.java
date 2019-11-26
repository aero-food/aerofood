package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    public MenuItem findMenuItemByTitleAndRestaurant(String title, Restaurant restaurant);
}
