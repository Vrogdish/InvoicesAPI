package com.Invoices.myApi.entities;

import com.Invoices.myApi.enums.Civility;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "profile", schema = "public")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "user_id", updatable = false, nullable = false)
    private long uid;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "company")
    private String company;

    @Enumerated(EnumType.STRING)
    @Column(name = "civility")
    private Civility civility;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "route")
    private String route; 

    @Column(name = "locality")
    private String locality; 

    @Column(name = "postal_code")
    private String postalCode; 

    @Column(name = "country")
    private String country; 
    
    @Column(name = "place_id")
    private String placeId; 

    public ProfileEntity(long uid, String photoUrl, String company, Civility civility, String firstname, String lastname, String email, String phone, String streetNumber, String route, String locality, String postalCode, String country, String placeId) {
        this.uid = uid;
        this.photoUrl = photoUrl;
        this.company = company;
        this.civility = civility;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.streetNumber = streetNumber;
        this.route = route;
        this.locality = locality;
        this.postalCode = postalCode;
        this.country = country;
        this.placeId = placeId;
    }

}
