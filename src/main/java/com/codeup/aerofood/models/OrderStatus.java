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
}
