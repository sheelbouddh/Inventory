package com.inventory.product.exceptionHandler;

import com.inventory.product.exception.InventoryDatabaseError;
import com.inventory.product.exception.InventoryInternalException;
import com.inventory.product.exception.ProductNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleIntegrityViolation(DataIntegrityViolationException ex){
        return "Data passed is not valid";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String jsonParseError(HttpMessageNotReadableException ex){
        return "Data passed is not valid";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFound.class)
    public String dataNotFound(ProductNotFound ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(InventoryDatabaseError.class)
    public String emptyDatabse(InventoryDatabaseError ex){
        return ex.getMessage();
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InventoryInternalException.class)
    public String internalError(InventoryInternalException ex){
        return ex.getMessage();
    }
}

