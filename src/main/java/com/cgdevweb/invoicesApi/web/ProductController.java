package com.cgdevweb.invoicesApi.web;

import com.cgdevweb.invoicesApi.ProductExample;
import com.cgdevweb.invoicesApi.models.Product;
import com.cgdevweb.invoicesApi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
                                    schema = @Schema(implementation = Product.class)
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
                    description = "Product",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            )
    })
    @GetMapping("/{id}")
    public Product getProductById(
            @Parameter(description = "ID of the product to be fetched ", required = true)
            @PathVariable String id
    ) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Create a product", description = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class)
                            )
                    }
            )
    })
    @PostMapping
    public Product createProduct(
            @RequestBody @Valid Product product) {
        return product;
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated"
            )
    })
    @PutMapping("/{id}")
    public void updateProduct(
            @Parameter(description = "ID of the product to update")
            @PathVariable String id,
            @RequestBody @Valid Product product
    ) {

    }

    @Operation(summary = "Delete a product", description = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product deleted"
            )
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @Parameter(description = "ID of the product to delete")
            @PathVariable
            String id
    ) {

    }
}
