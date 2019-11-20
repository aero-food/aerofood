package com.codeup.aerofood.models;


import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "INT")
    private Integer quantity;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String description;


    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private Float price;

    @Column(nullable = false, columnDefinition = "INT")
    private Integer dish_type;

    @Column (nullable = false, columnDefinition = "INT")
    private Integer menuItem;


    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;

    public OrderDetail() {

    }

    public OrderDetail(Integer quantity, String description, Float price, Integer dish_type, Integer menuItem, Orders orders) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.dish_type = dish_type;
        this.menuItem = menuItem;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

//    public MenuItem getMenuItem() {
//        return menuItem;
//    }
//
//    public void setMenuItem(MenuItem menuItem) {
//        this.menuItem = menuItem;
//    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }


}
