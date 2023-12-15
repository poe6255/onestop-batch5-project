package com.example.securitywithdynamicrole.entity.account;

import com.example.securitywithdynamicrole.entity.account.role.Role;
import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.address.Township;
import com.example.securitywithdynamicrole.entity.department.Department;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Employee extends Account{

    @NotBlank(message = "Phone Number cannot be blank!", groups = {Account.Update.class})
//    @Pattern(regexp = "^[0-9]{11}$",message = "Please enter valid phone number!", groups = {Account.Update.class})
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Cubicle Number cannot be blank!", groups = {Account.Update.class})
    @Pattern(regexp = "[A-Za-z0-9\\-]*",message = "Cubicle Number cannot contain illegal characters.", groups = {Account.Update.class})
    @Column(nullable = false)
    private String cubicleNumber;

    @NotNull(message = "Please select department.", groups = {Update.class})
    @ManyToOne
    private Department department;

    public static Employee admin(String username, String password, Role role, Township township){
        var admin = new Employee();
        admin.phoneNumber = "000";
        admin.cubicleNumber = "000";
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setRole(role);
        admin.setEmail("admin@admin.com");
        admin.setTownship(township);

        return admin;
    }
}
