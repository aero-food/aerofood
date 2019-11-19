package com.codeup.aerofood.repositories;


import com.codeup.aerofood.models.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
