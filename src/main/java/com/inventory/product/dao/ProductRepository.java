package com.inventory.product.dao;

import com.inventory.product.dto.Product;
import com.inventory.product.exception.InventoryDatabaseError;
import com.inventory.product.exception.ProductNotFound;
import com.inventory.product.exception.InventoryInternalException;

import java.util.List;

public interface ProductRepository {

    List<Product> list() throws InventoryInternalException, ProductNotFound, InventoryDatabaseError;

    void create(Product t) throws InventoryInternalException, InventoryDatabaseError;

    Product get(int id) throws ProductNotFound;

    void update(Product t, int id) throws ProductNotFound, InventoryInternalException;

    void delete(int id) throws ProductNotFound, InventoryInternalException;

}
