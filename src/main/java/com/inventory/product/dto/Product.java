package com.inventory.product.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int prodId;
    private String prodName;
    private int prodQuantity;
    private int sellerId;

    public Product() {
    }

    public Product(String prodName, int prodQuantity, int sellerId) {
        this.prodName = prodName;
        this.prodQuantity = prodQuantity;
        this.sellerId = sellerId;
    }
}
