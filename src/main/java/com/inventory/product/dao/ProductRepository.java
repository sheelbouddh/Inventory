package com.inventory.product.dao;

import com.inventory.product.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> list();

    void create(Product t);

    Product get(int id);

    void update(Product t, int id);

    void delete(int id);

}
