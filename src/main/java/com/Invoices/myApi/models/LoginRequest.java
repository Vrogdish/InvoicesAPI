package com.Invoices.myApi.models;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull (message = "username is reqquired") String username,
        @NotNull (message = "password is required") String password) {
}
