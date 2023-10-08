package com.example.jpaonetoone.ds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private Address address;

    public Customer(){

    }
    public void addAddress(Address address){
        address.setCustomer(this);
        this.address=address;
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
