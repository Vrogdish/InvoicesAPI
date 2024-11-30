package com.Invoices.myApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Invoices.myApi.entities.ProductEntity;
import com.Invoices.myApi.errors.RessourceNotFoundException;
import com.Invoices.myApi.models.Product;
import com.Invoices.myApi.models.ProductToCreate;
import com.Invoices.myApi.repositories.ProductRepository;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(product -> new Product(
                product.getId(),
                product.getUid(),
                product.getName(),
                product.getRef(),
                product.getDescription(),
                product.getPrice(),
                product.getTax())).toList();

    }

    public Product getProduct(long id) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new RessourceNotFoundException("Product with id" + id + " not found");
        } else {
            return new Product(
                    product.getId(),
                    product.getUid(),
                    product.getName(),
                    product.getRef(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getTax());
        }
    }

    public Product createProduct(ProductToCreate product) {
        long userId = 0000; // TODO: get user id from authentication

        ProductEntity productEntity = new ProductEntity(
                userId,
                product.name(),
                product.ref(),
                product.description(),
                product.price(),
                product.tax());
        ProductEntity createdProduct = productRepository.save(productEntity);
        return new Product(
                createdProduct.getId(),
                createdProduct.getUid(),
                createdProduct.getName(),
                createdProduct.getRef(),
                createdProduct.getDescription(),
                createdProduct.getPrice(),
                createdProduct.getTax());

    }

    public Product updateProduct(long id, ProductToCreate product) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity == null) {
            throw new RessourceNotFoundException("Product with id" + id + " not found");
        } else {
            productEntity.setName(product.name());
            productEntity.setRef(product.ref());
            productEntity.setDescription(product.description());
            productEntity.setPrice(product.price());
            productEntity.setTax(product.tax());
            ProductEntity updatedProduct = productRepository.save(productEntity);
            return new Product(
                    updatedProduct.getId(),
                    updatedProduct.getUid(),
                    updatedProduct.getName(),
                    updatedProduct.getRef(),
                    updatedProduct.getDescription(),
                    updatedProduct.getPrice(),
                    updatedProduct.getTax());
        }
    }

    public String deleteProduct(long id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity == null) {
            throw new RessourceNotFoundException("Product with id" + id + " not found");
        } else {
            productRepository.delete(productEntity);
            return "Product deleted";
        }
    }

}
