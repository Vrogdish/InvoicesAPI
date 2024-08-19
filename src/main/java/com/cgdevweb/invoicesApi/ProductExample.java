package com.cgdevweb.invoicesApi;

import com.cgdevweb.invoicesApi.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductExample {
    public static Product POMMES = new Product("1", "0000", "Pommes", "1 kilo", 4.0);
    public  static Product FRAISES = new Product("2", "0010", "Fraises", "Barquette de 500gr", 7.0);
    public static Product BANANES = new Product("3", "0050", "Bananes", "1 kilo", 3.5);

    public static List<Product> ALL = Arrays.asList(POMMES,FRAISES,BANANES);


}
