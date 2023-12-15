package com.example.securitywithdynamicrole.entity.account;

import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.address.Township;
import com.example.securitywithdynamicrole.entity.account.role.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please enter user name.", groups = {Update.class})
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Please enter password.", groups = {Create.class})
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Please enter email.", groups = {Update.class})
    @Email(message = "Please enter valid email.", groups = {Update.class})
    private String email;

    @NotNull(message = "Please select Role.", groups = {Update.class})
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Role role;

    @NotNull(message = "Please select township.", groups = {Update.class})
    @ManyToOne
    private Township township;

    @NotNull(message = "Please select state.", groups = {Update.class})
    @ManyToOne
    private State state;

    @Column(nullable = false)
    private boolean isActive = true;

    public interface Create{}
    public interface Update{}
}

