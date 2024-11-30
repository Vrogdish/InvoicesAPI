package com.Invoices.myApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Invoices.myApi.models.Product;
import com.Invoices.myApi.models.ProductToCreate;
import com.Invoices.myApi.services.ProductsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@Tag(name = "Products")

public class ProductsController {

        @Autowired
        ProductsService productsService;

        @Operation(summary = "Get all products", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Return all products", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product[].class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @GetMapping("/products")
        public ResponseEntity<List<Product>> getProducts() {
                List<Product> products = productsService.getProducts();
                return ResponseEntity.ok(products);
        }

        @Operation(summary = "Get product by id", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Return product by id", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "404", description = "Product not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @GetMapping("/products/{id}")
        public ResponseEntity<Product> getProductById(@Parameter(description = "Product id") @PathVariable long id) {
                Product product = productsService.getProduct(id);
                return ResponseEntity.ok(product);
        }

        @Operation(summary = "Create product", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "201", description = "Product created", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) })
        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @PostMapping("/products")
        public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductToCreate product) {
                Product createdProduct = productsService.createProduct(product);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        }

        @Operation(summary = "Update product", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Product updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) })
        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "404", description = "Product not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @PutMapping("/products/{id}")
        public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductToCreate product,
                        @Parameter(description = "Product id") @PathVariable long id) {
                Product updatedProduct = productsService.updateProduct(id, product);
                return ResponseEntity.ok(updatedProduct);
        }

        @Operation(summary = "Delete product", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Product deleted", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "404", description = "Product not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @DeleteMapping("/products/{id}")
        public ResponseEntity<String> deleteProduct(@Parameter(description = "Product id") @PathVariable long id) {
                String result = productsService.deleteProduct(id);
                return ResponseEntity.ok(result);
        }

}
