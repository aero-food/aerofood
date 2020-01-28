package com.codeup.aerofood.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String name;

//    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
//    private String thumbnail;

    @Column(columnDefinition = "VARCHAR(2000)")
    private String picture_url;

    @Column(columnDefinition = "Varchar(2000)")
    private String picture_credit;

    @Column(nullable = false, columnDefinition = "VARCHAR(3)")
    private String gate;

    @Column(nullable = false, columnDefinition = "VARCHAR(25)")
    private String phone_number;

    @Column(nullable = false, columnDefinition = "INT(1) DEFAULT 0")
    private int deleted;



    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinTable(
            name="restaurant_cuisine",
            joinColumns={@JoinColumn(name="restaurant_id")},
            inverseJoinColumns={@JoinColumn(name="cuisine_id")}
    )
    private Set<Cuisine> cuisines;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Orders> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<MenuItem> menu_items;

    @ManyToOne(optional=false)
    @JoinColumn(name="airport_id")
    private Airport airport;

    public Restaurant() {

    }

    public Restaurant(String name,  String picture_url, String gate, Airport airport, String phone_number) {
        this.name = name;
//        this.thumbnail = thumbnail;
        this.picture_url = picture_url;
        this.gate = gate;
        this.airport = airport;
        this.phone_number = phone_number;
    }

    public Restaurant(String name, String picture_url, String gate, String phone_number, int deleted, String picture_credit, Set<Cuisine> cuisines, List<Orders> orders, List<MenuItem> menu_items, Airport airport) {
        this.name = name;
//        this.thumbnail = thumbnail;
        this.picture_url = picture_url;
        this.gate = gate;
        this.phone_number = phone_number;
        this.deleted = deleted;
        this.picture_credit = picture_credit;
        this.cuisines = cuisines;
        this.orders = orders;
        this.menu_items = menu_items;
        this.airport = airport;
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

//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }

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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<MenuItem> getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(List<MenuItem> menu_items) {
        this.menu_items = menu_items;
    }

    public String getPicture_credit() {
        return picture_credit;
    }

    public void setPicture_credit(String picture_credit) {
        this.picture_credit = picture_credit;
    }
}
