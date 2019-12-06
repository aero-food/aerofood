package com.codeup.aerofood.models;
import javax.persistence.*;
import java.util.List;


@Entity
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String picture;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuCategory")
    private List<MenuItem> menu_items;

    public MenuCategory() {
    }

    public MenuCategory(String description, List<MenuItem> menu_items) {
        this.description = description;
        this.menu_items = menu_items;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<MenuItem> getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(List<MenuItem> menu_items) {
        this.menu_items = menu_items;
    }
}