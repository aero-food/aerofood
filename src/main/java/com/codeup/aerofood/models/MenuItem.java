package com.codeup.aerofood.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Comparator;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private BigDecimal price;

    @Column(columnDefinition = "VARCHAR(255)")
    private String picture_url;

    @ManyToOne
    @JoinColumn(name = "menu_category_id")
    private MenuCategory menuCategory;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public MenuItem() {

    }

    public MenuItem(String title, String description, BigDecimal price, MenuCategory menuCategory, Restaurant restaurant) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.menuCategory = menuCategory;
        this.restaurant = restaurant;
    }

    public MenuItem(String title, String description, BigDecimal price, Restaurant restaurant) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
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

    public BigDecimal getPrice() {
        return price;
    }

    public String getPriceString() {
        DecimalFormat df = new DecimalFormat("0.00##");
        String result = df.format(price);
        return result;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
//
//    public List<OrderDetail> getOrderDetails() {
//        return orderDetails;
//    }
//
//    public void setOrderDetails(List<OrderDetail> orderDetails) {
//        this.orderDetails = orderDetails;
//    }

    public static Comparator<MenuItem> MenuCategoryComparator = new Comparator<MenuItem>() {

        public int compare(MenuItem s1, MenuItem s2) {
            String MenuItem1 = s1.getMenuCategory().getDescription().toUpperCase();
            String MenuItem2 = s2.getMenuCategory().getDescription().toUpperCase();

            //ascending order
            return MenuItem1.compareTo(MenuItem2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};



    //    public static Comparator<MenuItem> getMenuCategoryComparator() {
//        return MenuCategoryComparator;
//    }
//
//    public static void setMenuCategoryComparator(Comparator<MenuItem> menuCategoryComparator) {
//        MenuCategoryComparator = menuCategoryComparator;
//    }
}

