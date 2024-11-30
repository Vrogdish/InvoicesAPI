package com.Invoices.myApi.models;

public record CustomUser(
    int id,
    String firstName,
    String lastName,
    String company,
    String email,
    String password,
    String address,
    String city,
    String postalCode
) {

}
