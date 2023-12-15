package com.example.securitywithdynamicrole.Dao.department;

import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface DepartmentDao extends JpaRepositoryImplementation<Department, Integer> {

    Optional<Department> findByName(String name);


}
