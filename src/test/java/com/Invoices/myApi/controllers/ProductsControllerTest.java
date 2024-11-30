package com.Invoices.myApi.controllers;

import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

import com.Invoices.myApi.models.Product;
import com.Invoices.myApi.services.ProductsService;

@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductsService productsService;

    @TestConfiguration
    static class ProductsControllerTestConfiguration {
        @Bean
        public ProductsService productsService(@Mock ProductsService mock) {
            return mock;
        }
    }

    @Test
    void testGetProducts() throws Exception {

        Product product1 = new Product(1, 1, "Pommes", "001", "Pommes de saison", 2.0, 0.2);
        Product product2 = new Product(2, 1, "Poires", "002", "Poires de saison", 2.5, 0.2);
        List<Product> mockProducts = List.of(product1, product2);

        when(productsService.getProducts()).thenReturn(mockProducts);

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product 2"));

        verify(productsService, times(1)).getProducts();

    }

}
