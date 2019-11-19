package com.codeup.aerofood.models;


import javax.persistence.*;

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

//    @OneToOne
//    private MenuItem menuItem;
//
//    @OneToOne(mappedBy = "menuitem")
//    private OrderDetail orderDetail;

    public MenuItem() {

    }

    public MenuItem(String title, String description, Float price, Integer dish_type, Restaurant restaurant, OrderDetail orderDetail) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.dish_type = dish_type;
        this.restaurant = restaurant;
        //this.orderDetail = orderDetail;
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

//    public OrderDetail getOrderDetail() {
//        return this.orderDetail;
//    }
//
//    public void setOrderDetail(OrderDetail orderDetail) {
//        this.orderDetail = orderDetail;
//    }
}

