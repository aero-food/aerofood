package com.codeup.aerofood.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "INT")
    private Integer quantity;


//    @OneToOne
//   private MenuItem menuItem;


    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "MenuItem")
    private MenuItem menuItem;

    public OrderDetail() {

    }

    public OrderDetail(Integer quantity, Orders orders, MenuItem menuItem) {
        this.quantity = quantity;
        this.orders = orders;
        this.menuItem = menuItem;
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getTotalPerItem(){
        int quantity = this.getQuantity();
        BigDecimal price = this.menuItem.getPrice();
        BigDecimal totalPerItem =  price.multiply(new BigDecimal(quantity));

        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(totalPerItem);
    }
}
