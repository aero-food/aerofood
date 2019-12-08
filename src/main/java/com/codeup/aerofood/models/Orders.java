package com.codeup.aerofood.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(3)")
    private String gate;

    @Column(columnDefinition = "DATETIME")
    private Date delivery_time;

    @Column(columnDefinition = "DATETIME")
    private Date ordered_time;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal total;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal tax;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal subTotal;

    @Column(nullable = false, columnDefinition = "INT(11) DEFAULT 0")
    private int orderStatus;

    @Column(columnDefinition = "VARCHAR(25)")
    private String orderStatusString;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "orderStatus_id", columnDefinition = " int(11) DEFAULT 0")
//    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderDetail> orderDetails;

    public Orders(){

    }

    public Orders(String gate,
                  String delivery_time,
                  BigDecimal total,
                  String ordered_status,
                  User user,
                  Restaurant restaurant,
                  List<OrderDetail> orderDetails) {
        this.gate = gate;
        this.total = total;
        this.user = user;
        this.restaurant = restaurant;
        this.orderDetails = orderDetails;
    }
    public Orders(String gate,
                  String delivery_time,
                  Date ordered_time,
                  BigDecimal total,
                  String ordered_status,
                  User user,
                  Restaurant restaurant)
    {
        this.gate = gate;
        this.total = total;
        this.ordered_time = ordered_time;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Orders(String gate,
                  BigDecimal total,
                  User user){
        this.gate = gate;
        this.total = total;
        this.user = user;

    }

    public Orders(String gate,
                  Date delivery_time,
                  Date ordered_time,
                  BigDecimal total,
                  BigDecimal tax,
                  BigDecimal subTotal,
                  User user) {
        this.gate = gate;
        this.delivery_time = delivery_time;
        this.ordered_time = ordered_time;
        this.total = total;
        this.tax = tax;
        this.subTotal = subTotal;
        this.user = user;
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

    public Date getDelivery_time() {
        return delivery_time;
    }

    public String getDelivery_time_String() {
        String pattern = "MM/dd/yyyy HH:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(delivery_time);
        return date;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }

    public Date getOrdered_time() {
        return ordered_time;
    }

    public String getOrdered_time_String() {
        String pattern = "MM/dd/yyyy HH:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(ordered_time);
        return date;
    }

    public void setOrdered_time(Date ordered_time) {
        this.ordered_time = ordered_time;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getOrderStatusString() {
        return orderStatusString;
    }

    public void setOrderStatusString(String orderStatusString) {
        this.orderStatusString = orderStatusString;
    }
}
