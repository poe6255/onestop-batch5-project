package com.example.securitywithdynamicrole.Dao.account;

import com.example.securitywithdynamicrole.entity.account.Employee;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface EmployeeDao extends JpaRepositoryImplementation<Employee, Integer> {


    @Query(value = "select e from Employee e where e.username not like 'admin'",
            countQuery = "select count(e) from Employee e"
    )
    Page<Employee> findAll(Pageable pageable);
}
