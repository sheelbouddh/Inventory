package com.inventory.product.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private String prodName;
    private int prodQuantity;
    private int sellerId;
}
