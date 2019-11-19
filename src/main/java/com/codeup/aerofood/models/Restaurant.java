package com.codeup.aerofood.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false, unique=true,  columnDefinition = "TEXT")
    private String thumbnail;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String picture_url;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(3)")
    private String gate;

    @Column(nullable = false, unique=true,  columnDefinition = "VARCHAR(3)")
    private String airport;

    @Column(nullable = false, columnDefinition = "INT(10)")
    private String phone_number;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<MenuItem> menu_items;

    public Restaurant(){

    }

    public Restaurant(String name,
                      String thumbnail,
                      String picture_url,
                      String gate,
                      String airport,
                      String phone_number,
                      List<Order> orders,
                      List<MenuItem> menu_items) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.picture_url = picture_url;
        this.gate = gate;
        this.airport = airport;
        this.phone_number = phone_number;
        this.orders = orders;
        this.menu_items = menu_items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<MenuItem> getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(List<MenuItem> menu_items) {
        this.menu_items = menu_items;
    }
}
