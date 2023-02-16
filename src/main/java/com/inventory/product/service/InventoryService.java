package com.inventory.product.service;

import com.inventory.product.dto.Product;
import com.inventory.product.dao.ProductRepositoryImpl;
import com.inventory.product.exception.InventoryDatabaseError;
import com.inventory.product.exception.NotAllowedToAdd;
import com.inventory.product.exception.ProductNotFound;
import com.inventory.product.exception.InventoryInternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InventoryService {

    private RestTemplate restTemplate = new RestTemplate();

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

    public void addProduct(Product product) throws InventoryInternalException, InventoryDatabaseError, NotAllowedToAdd {
        int userId = product.getSellerId();
        String url = "http://localhost:8081/get-role/";
        String userRole = "";

        try{
            userRole = restTemplate.getForObject(url+userId,String.class);
        }catch (Exception ex){
            throw new InventoryInternalException("Rest template error");
        }

        if(userRole.equals("Seller")){
            productRepository.create(product);
        }else{
            throw new NotAllowedToAdd("User with userId: "+ userId+" is a not allowed to Sell Something");
        }

    }

    public void updateProduct(int id, Product product) throws ProductNotFound, InventoryInternalException {
        productRepository.update(product, id);
    }

    public void deleteProduct(int id) throws InventoryInternalException, ProductNotFound {
        productRepository.delete(id);
    }
}
