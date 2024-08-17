package com.cgdevweb.invoicesApi.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Product(
        String id,
        String reference,
        @NotBlank String name,
        String description,
        int price
        ) {
}
