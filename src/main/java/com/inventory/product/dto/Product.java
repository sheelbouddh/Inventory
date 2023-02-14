package com.inventory.product.dto;


import jakarta.persistence.Entity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Product {

    private int prodId;
    @NotNull(message = "Product name shouldn't be null")
    private String prodName;
    @Pattern(regexp = "\\d*", message = "invalid product quantity")
    private int prodQuantity;
    @NotNull(message = "Seller id shouldn't be null")
    private int sellerId;

    public Product() {
    }

    public Product(String prodName, int prodQuantity, int sellerId) {
        this.prodName = prodName;
        this.prodQuantity = prodQuantity;
        this.sellerId = sellerId;
    }
}
