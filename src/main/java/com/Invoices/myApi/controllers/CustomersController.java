package com.Invoices.myApi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.Invoices.myApi.models.Customer;
import com.Invoices.myApi.models.CustomerToCreate;
import com.Invoices.myApi.services.CustomersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
@Tag(name = "Customers")
public class CustomersController {

 @Autowired
        CustomersService customersService;

        @Operation(summary = "Get all customers", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Return all customers", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Customer[].class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @GetMapping("/customers")
        public ResponseEntity<List<Customer>> getCustomers() {
                List<Customer> customers = customersService.getCustomers();
                return ResponseEntity.ok(customers);
        }

        @Operation(summary = "Get customer by id", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Return customer by id", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "404", description = "customer not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @GetMapping("/customers/{id}")
        public ResponseEntity<Customer> getCustomerById(@Parameter(description = "Customer id") @PathVariable long id) {
                Customer customers = customersService.getCustomer(id);
                return ResponseEntity.ok(customers);
        }

        @Operation(summary = "Create customer", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "201", description = "Customer created", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) })
        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @PostMapping("/customers")
        public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerToCreate customer) {
                Customer createdCustomer = customersService.createCustomer(customer);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        }

        @Operation(summary = "Update customer", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Customer updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) })
        @ApiResponse(responseCode = "400", description = "Bad request", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "404", description = "Customer not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @PutMapping("/customers/{id}")
        public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerToCreate customer,
                        @Parameter(description = "Customer id") @PathVariable long id) {
                Customer updatedCustomer = customersService.updateCustomer(id, customer);
                return ResponseEntity.ok(updatedCustomer);
        }

        @Operation(summary = "Delete Customer", security = @SecurityRequirement(name = "bearerAuth"))
        @ApiResponse(responseCode = "200", description = "Customer deleted", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "404", description = "Customer not found", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
        @DeleteMapping("/customers/{id}")
        public ResponseEntity<String> deleteCustomer(@Parameter(description = "Customer id") @PathVariable long id) {
                String result = customersService.deleteCustomer(id);
                return ResponseEntity.ok(result);
        }
    

}
