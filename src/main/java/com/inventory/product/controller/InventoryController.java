package com.inventory.product.controller;

import com.inventory.product.service.InventoryService;
import com.inventory.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/products")
    public List<Product> getAllProducts(){
        return inventoryService.getAllProducts();
    }

    @RequestMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){
        return inventoryService.getProductById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addProduct(@RequestBody Product product){
        inventoryService.addProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product){
        inventoryService.updateProduct(id, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable int id){
        inventoryService.deleteProduct(id);
    }
}
