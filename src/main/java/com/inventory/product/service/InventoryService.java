package com.inventory.product.service;

import com.inventory.product.dto.Product;
import com.inventory.product.dao.ProductRepositoryImpl;
import com.inventory.product.exception.InventoryDatabaseError;
import com.inventory.product.exception.ProductNotFound;
import com.inventory.product.exception.InventoryInternalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private ProductRepositoryImpl productRepository;

    public InventoryService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() throws InventoryInternalException, InventoryDatabaseError {
        return productRepository.list();
    }

    public Product getProductById(int id) throws ProductNotFound {
        Product product = productRepository.get(id);
        if(product == null){
            throw new ProductNotFound("Product not found with id: " + id);
        }
        return product;
    }

    public void addProduct(Product product) throws InventoryInternalException, InventoryDatabaseError {
        int userId = product.getSellerId();
        productRepository.create(product);
    }

    public void updateProduct(int id, Product product) throws ProductNotFound, InventoryInternalException {
        productRepository.update(product, id);
    }

    public void deleteProduct(int id) throws InventoryInternalException, ProductNotFound {
        productRepository.delete(id);
    }
}
