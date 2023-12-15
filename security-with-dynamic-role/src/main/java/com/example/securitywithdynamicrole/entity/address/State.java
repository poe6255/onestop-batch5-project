package com.example.securitywithdynamicrole.entity.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Please select state")
    @NotEmpty(message = "Please enter state name.")
    @NotBlank(message = "Please enter state name.")
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "state", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Township> townships = new ArrayList<>();

    public State(String name){
        this.name = name;
    }

    public void addTownship(Township township){
        this.townships.add(township);
        township.setState(this);
    }

    public void removeTownship(Township township){
        this.townships.remove(township);
        township.setState(null);
    }
}
