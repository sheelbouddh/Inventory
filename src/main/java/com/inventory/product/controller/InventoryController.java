package com.inventory.product.controller;

import com.inventory.product.exception.InventoryDatabaseError;
import com.inventory.product.exception.NotAllowedToAdd;
import com.inventory.product.exception.ProductNotFound;
import com.inventory.product.exception.InventoryInternalException;
import com.inventory.product.service.InventoryService;
import com.inventory.product.dto.Product;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryController {

    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws InventoryInternalException, InventoryDatabaseError {
        List<Product> products = inventoryService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) throws ProductNotFound {
        Product product = inventoryService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product) throws InventoryInternalException, InventoryDatabaseError, NotAllowedToAdd {
        inventoryService.addProduct(product);
        return new ResponseEntity<>("New Product Added: "+product.getProdName(), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody @Valid Product product) throws ProductNotFound, InventoryInternalException {
        inventoryService.updateProduct(id, product);
        return new ResponseEntity<>("Product Updated with id: "+ id, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) throws ProductNotFound, InventoryInternalException {
        inventoryService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted with id: "+ id, HttpStatus.OK);
    }
}
