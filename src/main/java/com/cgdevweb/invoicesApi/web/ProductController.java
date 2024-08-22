package com.cgdevweb.invoicesApi.web;


import com.cgdevweb.invoicesApi.models.Error;
import com.cgdevweb.invoicesApi.models.Product;
import com.cgdevweb.invoicesApi.models.ProductToCreate;
import com.cgdevweb.invoicesApi.models.ProductToUpdate;
import com.cgdevweb.invoicesApi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product API")
@RestController
@RequestMapping("api/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products", description = "Return a list of all products")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class))
                            )
                    }
            )
    })
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Get a product by id", description = "Return a product by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product retrieved",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product with specified id not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    }

            )
    })
    @GetMapping("/{id}")
    public Product getProductById(
            @Parameter(description = "ID of the product to be fetched ", required = true)
            @PathVariable long id
    ) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Create a product", description = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product created"

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "fields requires",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )
    })
    @PostMapping
    public void createProduct(@RequestBody @Valid ProductToCreate product) {
        productService.createProduct(product);
    }

    @Operation(summary = "Update a product", description = "Update a product with specified ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product with specified id not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "fields requires",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )

    })
    @PutMapping("/{id}")
    public Product updateProduct(
            @Parameter(description = "ID of the product to update")
            @PathVariable long id,
            @RequestBody @Valid ProductToUpdate productUpdate
    ) {
        return productService.updateProduct(id, productUpdate);
    }

    @Operation(summary = "Delete a product", description = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product with specified id not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    }

            )
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @Parameter(description = "ID of the product to delete")
            @PathVariable
            long id
    ) {
        productService.deleteProduct(id);
    }
}
