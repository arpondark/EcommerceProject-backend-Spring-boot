package com.arpon007.EcommerceProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street must contain atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min = 2, message = "Building must contain atleast 2 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 2, message = "City must contain atleast 2 characters")
    private String city;

    @NotBlank
    @Size(min = 2, message = "State must contain atleast 2 characters")
    private String state;

    @NotBlank
    @Size(min = 2, message = "Country must contain atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min = 5, message = "Zipcode must contain atleast 5 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
