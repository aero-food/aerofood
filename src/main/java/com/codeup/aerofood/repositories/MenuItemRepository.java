package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {
    public MenuItem findMenuItemByTitleAndRestaurant(String title, Restaurant restaurant);
    public List<MenuItem> findMenuItemByRestaurantIsNull();

    Optional<MenuItem> findById(Long id);


 public List<MenuItem> findMenuItemByRestaurantIsNullAndTitleIsNotIn(List<String> titles);
//    public List<MenuItem> findMenuItemByRestaurantIsNullAndOrderByMenuCategoryAndTitleIsNotIn(List<String> titles);
//    public List<MenuItem> findMenuItemByRestaurantIsNullOrderByMenuCategory(Restaurant restaurant);
}

