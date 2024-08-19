package com.cgdevweb.invoicesApi.service;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException (String id) {
        super("Product with id "+ id + " not found");
    }
}
