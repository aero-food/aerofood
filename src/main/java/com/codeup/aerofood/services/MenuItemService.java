package com.codeup.aerofood.services;

import com.codeup.aerofood.models.MenuItem;
import com.codeup.aerofood.repositories.MenuItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }


    public Page<MenuItem> findAllMenuItemsPageable(Pageable pageable){
        return menuItemRepository.findAll(pageable);
    }

    public Optional<MenuItem> findById(long id){
        return  menuItemRepository.findById(id);
    }

//    public MenuItem findById(long id){
//            return  menuItemRepository.findById(id).get();
//        }

    public List<MenuItem> getAll() {
        return (List<MenuItem>) menuItemRepository.findAll();
    }

//    public MenuItem findOne(long id){
//        return menuItemRepository.findOne(id).get();
//    }


    public MenuItem create(MenuItem menuItem){
        menuItemRepository.save(menuItem);
        return menuItem;
    }
    public MenuItem delete(MenuItem menuItem){
        menuItemRepository.delete((menuItem));
        return menuItem;
    }

    public MenuItem edit(MenuItem menuItem){
        menuItemRepository.save(menuItem);
        return menuItem;
    }

    public List<MenuItem> all() {
            return menuItemRepository.findAll();
    }

}
