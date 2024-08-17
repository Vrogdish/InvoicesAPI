package com.cgdevweb.invoicesApi.service;

import com.cgdevweb.invoicesApi.ProductExample;
import com.cgdevweb.invoicesApi.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    public List<Product> getAllProducts() {
        return ProductExample.ALL;
    }

    public Product getProductById (String id) {
        return ProductExample.ALL.stream()
                .filter(product -> product.id().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
