package com.inventory.product.service;

import com.inventory.product.dto.Product;
import com.inventory.product.dao.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private ProductRepositoryImpl productRepository;

    public InventoryService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.list();
    }

    public Product getProductById(int id) {
        return productRepository.get(id);
    }

    public void addProduct(Product product) {
        int userId = product.getSellerId();
        productRepository.create(product);
    }

    public void updateProduct(int id, Product product) {
        productRepository.update(product, id);
    }

    public void deleteProduct(int id) {
        productRepository.delete(id);
    }
}
