package com.inventory.product.controller;

import com.inventory.product.service.InventoryService;
import com.inventory.product.dto.Product;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InventoryController {

    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = inventoryService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = inventoryService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product){
        try {
            inventoryService.addProduct(product);
            return new ResponseEntity<>("New Product Added", HttpStatus.CREATED);
        }catch (HttpMessageNotReadableException e){
            return new ResponseEntity<>("Invalid Input", HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody @Valid Product product){
        try{
            inventoryService.updateProduct(id, product);
            return new ResponseEntity<>("Product Updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Invalid Input", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        try {
            inventoryService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted with id: "+ id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
