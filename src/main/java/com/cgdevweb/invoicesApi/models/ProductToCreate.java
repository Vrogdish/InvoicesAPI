package com.cgdevweb.invoicesApi.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductToCreate(
        String reference,
        @NotBlank(message = "Name is required") String name,
        String description,
        @Positive(message = "price must be a positive number") Double price,
        @Positive (message = "uid must be a positive number") long uid
) {
}
