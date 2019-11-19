package com.codeup.aerofood.models;


import javax.persistence.*;

@Entity
@Table(name="menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String description;


    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private Float price;

    @Column(nullable = false, columnDefinition = "INT")
    private Integer dish_type;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToOne(mappedBy = "menuitem")
    private MenuItem menuItem;

    public MenuItem() {

    }

    public MenuItem(String title, String description, Float price, Integer dish_type, Restaurant restaurant, MenuItem menuItem) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.dish_type = dish_type;
        this.restaurant = restaurant;
        this.menuItem = menuItem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDish_type() {
        return dish_type;
    }

    public void setDish_type(Integer dish_type) {
        this.dish_type = dish_type;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
//            `dish_type_id` INT NOT NULL,
//
//    INDEX `fk_Menu_items_Restaurants1_idx` (`restaurant_id` ASC) VISIBLE,
//    INDEX `fk_Menu_items_dish_type1_idx` (`dish_type_id` ASC) VISIBLE,
//    CONSTRAINT `fk_Menu_items_Restaurants1`
//    FOREIGN KEY (`restaurant_id`)
//    REFERENCES `aero_food`.`restaurant` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION,
//    CONSTRAINT `fk_Menu_items_dish_type1`
//    FOREIGN KEY (`dish_type_id`)
//    REFERENCES `aero_food`.`menu_category` (`id`)
}

//
//`id` INT NOT NULL AUTO_INCREMENT,
//        `title` VARCHAR(255) NOT NULL,
//        `description` TEXT NOT NULL,
//        `price` DECIMAL(4,2) UNSIGNED NOT NULL,
//        `restaurant_id` INT NOT NULL,
//        `dish_type_id` INT NOT NULL,
//        PRIMARY KEY (`id`),
//        INDEX `fk_Menu_items_Restaurants1_idx` (`restaurant_id` ASC) VISIBLE,
//        INDEX `fk_Menu_items_dish_type1_idx` (`dish_type_id` ASC) VISIBLE,
//        CONSTRAINT `fk_Menu_items_Restaurants1`
//        FOREIGN KEY (`restaurant_id`)
//        REFERENCES `aero_food`.`restaurant` (`id`)
//        ON DELETE NO ACTION
//        ON UPDATE NO ACTION,
//        CONSTRAINT `fk_Menu_items_dish_type1`
//        FOREIGN KEY (`dish_type_id`)
//        REFERENCES `aero_food`.`menu_category` (`id`)