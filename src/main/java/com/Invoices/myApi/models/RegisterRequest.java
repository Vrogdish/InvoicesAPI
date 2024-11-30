package com.Invoices.myApi.models;

import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
    @NotNull String username,
    @NotNull String email,
    @NotNull String password
) {
    
}
