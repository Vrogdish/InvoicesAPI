package com.cgdevweb.invoicesApi.service;

import com.cgdevweb.invoicesApi.data.ProductEntity;
import com.cgdevweb.invoicesApi.data.ProductRepository;
import com.cgdevweb.invoicesApi.models.Product;
import com.cgdevweb.invoicesApi.models.ProductToCreate;
import com.cgdevweb.invoicesApi.models.ProductToUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new Product(
                        product.getId(),
                        product.getReference(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getUid()
                ))
                .toList();

    }

    @Transactional
    public Product getProductById(long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return new Product(
                product.get().getId(),
                product.get().getReference(),
                product.get().getName(),
                product.get().getDescription(),
                product.get().getPrice(),
                product.get().getUid()
        );

    }

    @Transactional
    public void createProduct(ProductToCreate product) {
        ProductEntity productEntity = new ProductEntity(
                product.reference(),
                product.name(),
                product.description(),
                product.price(),
                product.uid()
        );
        productRepository.save(productEntity);
    }

    @Transactional
    public Product updateProduct(long id, ProductToUpdate updatedProduct) {
        Optional<ProductEntity> productBeforeUpdate = productRepository.findById(id);
        if (productBeforeUpdate.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        ProductEntity product = productBeforeUpdate.get();

        product.setName(updatedProduct.name());
        product.setReference(updatedProduct.reference());
        product.setDescription(updatedProduct.description());
        product.setPrice(updatedProduct.price());


        productRepository.save(product);

        return getProductById(id);
    }

    @Transactional
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}

