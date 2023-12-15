package com.example.securitywithdynamicrole.entity.account.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "urls")
    private List<Role> roles = new ArrayList<>();

    public Url(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void removeRole(Role role){
        roles.remove(role);
    }

}
