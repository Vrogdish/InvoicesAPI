package com.Invoices.myApi.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductToCreate(
        @NotNull(message = "Name is required") String name,
        String ref,
        String description,
        @Positive(message = "Price must be a positive number") double price,
        @Positive(message = "Tax must be a number between 0 and 100") double tax
) {

}
