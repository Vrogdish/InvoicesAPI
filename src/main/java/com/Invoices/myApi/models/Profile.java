package com.Invoices.myApi.models;

import com.Invoices.myApi.enums.Civility;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Profile(
        long id,
        long uid,
        String photoUrl,
        String company,
        Civility civility,
        @NotBlank String firstname,
        @NotBlank String lastname,
        @Email String email,
        @Pattern(regexp = "\\+?[0-9]+", message = "Invalid phone number") String phone,
        String streetNumber,
        String route,
        String locality,
        String postalCode,
        String country,
        String placeId) {
}
