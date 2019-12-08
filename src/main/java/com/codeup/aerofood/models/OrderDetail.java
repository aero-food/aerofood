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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private BigDecimal price;


//    @OneToOne
//   private MenuItem menuItem;


    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;

//    @ManyToOne
//    @JoinColumn(name = "MenuItem")
//    private MenuItem menuItem;

    public OrderDetail() {

    }

    public OrderDetail(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public OrderDetail(String description, BigDecimal price, Orders orders) {
        this.description = description;
        this.price = price;
        this.orders = orders;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }


    public String getTotalPerItem(){
        BigDecimal totalPerItem =  this.getPrice();

        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(totalPerItem);
    }
}
