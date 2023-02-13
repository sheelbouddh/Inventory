package com.inventory.product.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private int prodId;

    @Column(name = "product_id")
    private String prodName;

    @Column(name = "product_quantity")
    private int prodQuantity;
    @Column(name = "seller_id")
    private int sellerId;
}
