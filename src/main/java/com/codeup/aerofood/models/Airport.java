package com.codeup.aerofood.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false, columnDefinition = "INT(1) DEFAULT 0")
    private int deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airport")
    private Set<Restaurant> restaurants;
    //new HashSet<>();

    public Airport() {
    }

    public Airport(String name) {
        Name = name;
    }

    public Airport(String name, Set<Restaurant> restaurants) {
        Name = name;
        this.restaurants = restaurants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
