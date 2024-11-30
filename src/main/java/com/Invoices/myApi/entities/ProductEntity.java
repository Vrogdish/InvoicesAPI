package com.Invoices.myApi.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products", schema = "public")

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "user_id", updatable = false, nullable = false)
    private long uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ref")
    private String ref;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "tax", nullable = false)
    private double tax;

    public ProductEntity(long uid, String name, String ref, String description, double price, double tax) {
        this.uid = uid;
        this.name = name;
        this.ref = ref;
        this.description = description;
        this.price = price;
        this.tax = tax;
    }
  
}
