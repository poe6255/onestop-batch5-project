package com.example.jpaonetoone.ds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String streetName;
    private String city;
    @OneToOne
    private Customer customer;

    public Address(){}

    public Address(String streetName, String city) {
        this.streetName = streetName;
        this.city = city;
    }
}
