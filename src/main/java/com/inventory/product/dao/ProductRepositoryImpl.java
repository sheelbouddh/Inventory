package com.inventory.product.dao;

import com.inventory.product.dto.Product;
import com.inventory.product.exception.InventoryDatabaseError;
import com.inventory.product.exception.ProductNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.inventory.product.exception.InventoryInternalException;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private final String INSERT_PRODUCT = "INSERT INTO products(product_name,product_quantity,seller_id) VALUES(?,?,?)";
    private final String GET_PRODUCT_WITH_ID = "SELECT * FROM products WHERE product_id = ?";
    private final String UPDATE_PRODUCT = "UPDATE products SET product_name = ?, product_quantity = ?, seller_id = ? WHERE product_id = ?";
    private final String DELETE_PRODUCT = "DELETE FROM products WHERE product_id = ?";

    RowMapper<Product> rowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setProdId(rs.getInt("product_id"));
        product.setProdName(rs.getString("product_name"));
        product.setProdQuantity(rs.getInt("product_quantity"));
        product.setSellerId(rs.getInt("seller_id"));
        return product;
    };

    @Override
    public List<Product> list() throws InventoryInternalException, InventoryDatabaseError {
        List<Product> products = null;

        try {
            products = jdbcTemplate.query(GET_ALL_PRODUCTS, rowMapper);
        }catch (Exception ex){
            throw new InventoryInternalException(ex.getMessage());
        }

        if (products.isEmpty()){
            throw new InventoryDatabaseError("Products not found!");
        }

        return products;
    }


    @Override
    public void create(Product p) throws InventoryInternalException, InventoryDatabaseError {
        int insert = 0;

        try {
            insert = jdbcTemplate.update(INSERT_PRODUCT, p.getProdName(), p.getProdQuantity(), p.getSellerId());
        }catch (Exception ex){
            throw new InventoryInternalException(ex.getMessage());
        }

        if(insert == 1) log.info("New product added: " + p.getProdName());
        else throw new InventoryDatabaseError("Product not added");
    }

    @Override
    public Product get(int id) throws ProductNotFound {
        Product product = null;
        try{
            product = jdbcTemplate.queryForObject(GET_PRODUCT_WITH_ID, new Object[]{id}, rowMapper);
        }catch (DataAccessException ex){
            throw new ProductNotFound("Product not found with id: " + id);
        }
        return product;
    }

    @Override
    public void update(Product p, int id) throws ProductNotFound, InventoryInternalException {
        int update = 0;

        try {
            update = jdbcTemplate.update(UPDATE_PRODUCT, p.getProdName(), p.getProdQuantity(), p.getSellerId(), id);
        }catch (Exception ex){
            throw new InventoryInternalException(ex.getMessage());
        }

        if (update == 1) log.info("Product updated with id: " + id);
        else {
            throw new ProductNotFound("Product not found with id: " + id);
        }
    }

    @Override
    public void delete(int id) throws InventoryInternalException, ProductNotFound {
        int delete = 0;
        try{
            delete = jdbcTemplate.update(DELETE_PRODUCT, id);
        }catch (Exception ex){
            throw new InventoryInternalException(ex.getMessage());
        }

        if (delete == 1) log.info("Product removed with id: "+id);
        else throw new ProductNotFound("Product not found with id: " + id);
    }
}
