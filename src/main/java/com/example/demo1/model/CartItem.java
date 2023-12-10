package com.example.demo1.model;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "userID")
    private int userId;

    public CartItem() {
    }


    // Dans votre classe CartItem
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // toString() for debugging or logging

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
