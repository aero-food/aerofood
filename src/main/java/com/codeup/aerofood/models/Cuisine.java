package com.codeup.aerofood.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    private Restaurant restaurant;

//    @ManyToMany(mappedBy = "cuisines")
//    private List<Restaurant> restaurant;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cuisines")
    private Set<Restaurant> restaurants;
    //new HashSet<>();

    public Cuisine() {
    }

    public Cuisine(String description) {
        this.description = description;
    }

    public Cuisine(String description, Set<Restaurant> restaurant) {
        this.description = description;
        this.restaurants = restaurant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
