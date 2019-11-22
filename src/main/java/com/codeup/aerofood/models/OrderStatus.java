package com.codeup.aerofood.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderStatus")
    private List<Orders> orders;

    public OrderStatus() {
    }

    public OrderStatus(String status, List<Orders> orders) {
        this.status = status;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
