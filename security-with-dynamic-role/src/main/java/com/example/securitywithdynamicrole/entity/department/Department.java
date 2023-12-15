package com.example.securitywithdynamicrole.entity.department;

import com.example.securitywithdynamicrole.entity.account.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Please enter department name.")
    @NotBlank(message = "Please enter department name.")
    @Pattern(regexp = "[A-Za-z- ]*",message = "Department name cannot contain illegal characters.")
    private String name;

    @OneToMany(mappedBy = "department", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Employee> employees = new ArrayList<>();

    public Department(String name){
        this.name = name;
    }
    public void addEmployee(Employee employee){
        this.employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
        employee.setDepartment(null);
    }

}
