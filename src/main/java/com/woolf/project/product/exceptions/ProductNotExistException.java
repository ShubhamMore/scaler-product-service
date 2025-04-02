package com.woolf.project.product.exceptions;


public class ProductNotExistException extends Exception {

    public ProductNotExistException(String message) {
        super(message);
    }
}