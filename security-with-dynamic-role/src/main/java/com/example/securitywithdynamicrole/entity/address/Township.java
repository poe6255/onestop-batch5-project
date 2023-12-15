package com.example.securitywithdynamicrole.entity.address;

import com.example.securitywithdynamicrole.entity.account.Account;
import com.example.securitywithdynamicrole.entity.account.Employee;
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
public class Township {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull(message = "Please select township.", groups = {Account.Update.class})
    @JsonIgnore
    private State state;

    @NotEmpty(message = "Please enter township name.")
    @NotBlank(message = "Please enter township name.")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "township")
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    public Township(String name) {
        this.name = name;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
        account.setTownship(this);
    }

    public void removeEmployee(Account account){
        this.accounts.remove(account);
        account.setTownship(null);
    }
}
