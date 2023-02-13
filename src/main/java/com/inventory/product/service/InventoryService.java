package com.inventory.product.service;

import com.inventory.product.dto.Product;
import com.inventory.product.dao.ProductRepositoryImpl;
import com.inventory.product.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private RestTemplate restTemplate;

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
//        int userId = product.getSellerId();
//        String url = "xyz";
//        String userRole = restTemplate.getForObject(url, User.class).getUserRole();
//        if(userRole == "Seller"){
//            productRepository.create(product);
//        }
        productRepository.create(product);
    }

    public void updateProduct(int id, Product product) {
        productRepository.update(product, id);
    }

    public void deleteProduct(int id) {
        productRepository.delete(id);
    }
}
