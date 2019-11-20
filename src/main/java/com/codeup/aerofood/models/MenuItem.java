package com.codeup.aerofood.models;


import javax.persistence.*;
import java.util.List;

@Entity
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

//    @OneToOne(mappedBy = "menuItem")
//    private OrderDetail orderDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuItem")
    private List<OrderDetail> orderDetails;

    public MenuItem() {

    }

    public MenuItem(String title, Float price, List<OrderDetail> orderDetails1) {
        this.title = title;
        this.price = price;
        this.orderDetails = orderDetails;

    }

    public MenuItem(String title,
                    String description,
                    Float price,
                    Integer dish_type,
                    Restaurant restaurant,
                    List<OrderDetail> orderDetails) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.dish_type = dish_type;
        this.restaurant = restaurant;
        this.orderDetails = orderDetails;
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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

