package com.example.securitywithdynamicrole.entity.account.role;

import com.example.securitywithdynamicrole.entity.account.Account;
import com.example.securitywithdynamicrole.entity.account.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotBlank(message = "Please enter role name.")
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Url> urls = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<Account> accounts = new ArrayList<>();

    public Role(int id, String name, List<Url> urls) {
        this.id = id;
        this.name = name;
        urls.forEach(this::addUrl);
    }

    public void addUrl(Url url){
        this.urls.add(url);
        url.getRoles().add(this);
    }

    public void removeUrl(Url role){
        this.urls.remove(role);
        role.getRoles().remove(this);
    }

    public void addAllUrl(List<Url> urls) {
        this.urls = urls;
        urls.forEach(url -> url.getRoles().add(this));
    }

    public void removeAccount(Employee exists) {
        accounts.remove(exists);
    }

    public String toString() {
        return  name ;
    }
}
