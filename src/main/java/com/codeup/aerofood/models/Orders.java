package com.codeup.aerofood.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(3)")
    private String gate;

    @Column(nullable = false, unique=true,  columnDefinition = "DATETIME")
    private String delivery_time;

    @Column(nullable = false, unique=true,  columnDefinition = "DATETIME")
    private String ordered_time;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Float total;

    @Column(nullable = false, unique=true,  columnDefinition = "VARCHAR(1)")
    private String ordered_status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "orderStatus_id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderDetail> orderDetails;

    public Orders(){

    }

    public Orders(String gate,
                  String delivery_time,
                  String ordered_time,
                  Float total,
                  String ordered_status,
                  User user,
                  Restaurant restaurant,
                  List<OrderDetail> orderDetails) {
        this.gate = gate;
        this.delivery_time = delivery_time;
        this.ordered_time = ordered_time;
        this.total = total;
        this.ordered_status = ordered_status;
        this.user = user;
        this.restaurant = restaurant;
        this.orderDetails = orderDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getOrdered_time() {
        return ordered_time;
    }

    public void setOrdered_time(String ordered_time) {
        this.ordered_time = ordered_time;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }


    public String getOrdered_status() {
        return ordered_status;
    }

    public void setOrdered_status(String ordered_status) {
        this.ordered_status = ordered_status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
