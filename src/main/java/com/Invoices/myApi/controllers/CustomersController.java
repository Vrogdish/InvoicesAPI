package com.Invoices.myApi.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@Tag(name = "Customers")
public class CustomersController {

    @GetMapping("/customers")
    public String getCustomers() {
        return new String();
    }
    

}
