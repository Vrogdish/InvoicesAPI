package com.cgdevweb.invoicesApi.service;

import com.cgdevweb.invoicesApi.ProductExample;
import com.cgdevweb.invoicesApi.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>(ProductExample.ALL);


    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String id) {
        return products.stream()
                .filter(product -> product.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        Product newProduct = new Product(
                product.id(), product.reference(), product.name(), product.description(), product.price()
        );
        products.add(newProduct);

        return newProduct;
    }

    public Product updateProduct(String id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        int index = products.indexOf(existingProduct);
        products.set(index, updatedProduct);

        return updatedProduct;
    }

    public void deleteProduct(String id) {
        Product existingProduct = getProductById(id);
        int index = products.indexOf(existingProduct);
        products.remove(index);
    }
}

