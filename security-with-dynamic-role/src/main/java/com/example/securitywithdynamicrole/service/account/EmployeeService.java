package com.example.securitywithdynamicrole.service.account;

import com.example.securitywithdynamicrole.Dao.account.EmployeeDao;
import com.example.securitywithdynamicrole.entity.account.Account;
import com.example.securitywithdynamicrole.entity.account.Customer;
import com.example.securitywithdynamicrole.entity.account.Employee;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import com.example.securitywithdynamicrole.service.department.DepartmentService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Value("${my.page_size}")
    public int pageSize;
    private final EmployeeDao employeeDao;
    private final DepartmentService departmentService;
    private final RoleService roleService;

    public Employee findById(int id){
        return employeeDao.findById(id).orElseThrow(EntityExistsException::new);
    }
    public Optional<Employee> optionalEmployee(int id){
        if(id < 1){
            return Optional.empty();
        }
        return employeeDao.findById(id);
    }

    public Page<Employee> findEmployee(Optional<String> key, Pageable pageable) {
//        var pagable = PageRequest.of(0, pageSize);

//        if (pageNumber.isPresent()) {
//            pagable = PageRequest.of(pageNumber.get() - 1, pageSize);
//        }

        if (key.isEmpty()) {
            return employeeDao.findAll(pageable);
        }

        try {
            var id = Integer.parseInt(key.get());
            return optionalEmployee(id)
                    .map(employee -> new PageImpl<>(List.of(employee)))
                    .orElseGet(() -> new PageImpl<>(Collections.emptyList()));

        } catch (NumberFormatException e) {
            var word = "%".concat(key.get() + "%").toLowerCase();

            Specification<Employee> name = (root, query, cb) -> cb.like(cb.lower(root.get("username")), word);
            Specification<Employee> notAdmin = (root, query, cb) -> cb.notLike(cb.lower(root.get("username")), "admin");
            Specification<Employee> email = (root, query, cb) -> cb.like(cb.lower(root.get("phoneNumber")), word);


            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").ascending());

            return employeeDao.findAll(Specification.anyOf(name, email).and(notAdmin), pageable);
        }
    }
    public Page<Employee> findEmployee(Optional<String> key, Optional<Integer> pageNumber){

        var p = PageRequest.of(0, pageSize);
        if(pageNumber.isPresent()){
            p = PageRequest.of(pageNumber.get() - 1, pageSize);

        }
        if(key.isEmpty()){
            return employeeDao.findAll(p);
        }

        try{
            var id = Integer.parseInt(key.get());
            return optionalEmployee(id)
                        .map(employee -> new PageImpl<Employee>(List.of(employee)))
                        .orElseGet(() -> new PageImpl<>(Collections.emptyList()));

        }catch (NumberFormatException e){
            var word = "%".concat(key.get() + "%").toLowerCase();

            Specification<Employee> name = (root, query, cb) -> cb.like(cb.lower(root.get("username")), word);
            Specification<Employee> notAdmin = (root, query, cb) -> cb.notLike(cb.lower(root.get("username")), "admin");
            Specification<Employee> email = (root, query, cb) -> cb.like(cb.lower(root.get("phoneNumber")), word);
            return employeeDao.findAll(Specification.anyOf(name, email).and(notAdmin), p);
        }
    }

    public Employee save(Employee employee){
        return employeeDao.save(employee);
    }

    public boolean delete(int id){
        if(employeeDao.existsById(id)){
            var e = findById(id);
            e.setActive(false);
            employeeDao.saveAndFlush(e);
            return true;
        }
        return false;
    }

    public Employee update(Employee employee, int id){
        var exists = findById(id);
        BeanUtils.copyProperties(employee, exists, "id", "password");
        return employeeDao.saveAndFlush(exists);
    }

}
