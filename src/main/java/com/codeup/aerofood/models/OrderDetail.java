package com.codeup.aerofood.models;


import javax.persistence.*;

@Entity
@Table(name="order_details")
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


    @OneToOne
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetail(){

    }

    public OrderDetail(Integer quantity, String description, Float price, Integer dish_type, MenuItem menuItem, Order order) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.dish_type = dish_type;
        this.menuItem = menuItem;
        this.order = order;
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

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //  `menu_item_id` INT NOT NULL,
//            `order_id` INT NOT NULL,
//    PRIMARY KEY (`id`, `menu_item_id`, `order_id`),
//    INDEX `fk_order_details_Menu_items1_idx` (`menu_item_id` ASC) VISIBLE,
//    INDEX `fk_order_details_orders1_idx` (`order_id` ASC) VISIBLE,
//    CONSTRAINT `fk_order_details_Menu_items1`
//    FOREIGN KEY (`menu_item_id`)
//    REFERENCES `aero_food`.`menu_item` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION,
//    CONSTRAINT `fk_order_details_orders1`
//    FOREIGN KEY (`order_id`)
//    REFERENCES `aero_food`.`order` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION)
}
