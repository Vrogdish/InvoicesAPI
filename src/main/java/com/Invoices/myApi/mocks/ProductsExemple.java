package com.Invoices.myApi.mocks;

import java.util.ArrayList;
import java.util.List;

import com.Invoices.myApi.models.Product;

public class ProductsExemple {
    public static Product Fraises = new Product(1, 1, "Fraises","", "Fraises de saison", 2.5, 0.2);
    public static Product Bananes = new Product(2, 1, "Bananes","", "Bananes de saison", 1.5, 0.2);
    public static Product Pommes = new Product(3, 1, "Pommes","", "Pommes de saison", 2.0, 0.2);

    public static List<Product> allProducts = new ArrayList<Product>( List.of(Fraises, Bananes, Pommes) );
}
