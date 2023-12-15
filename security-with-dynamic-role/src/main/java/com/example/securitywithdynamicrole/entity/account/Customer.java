package com.example.securitywithdynamicrole.entity.account;

import com.example.securitywithdynamicrole.entity.account.role.Role;
import com.example.securitywithdynamicrole.entity.address.Township;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends Account{
}
