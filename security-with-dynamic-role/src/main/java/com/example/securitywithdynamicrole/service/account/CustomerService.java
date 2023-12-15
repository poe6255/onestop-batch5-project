package com.example.securitywithdynamicrole.service.account;

import com.example.securitywithdynamicrole.Dao.account.CustomerDao;
import com.example.securitywithdynamicrole.entity.account.Customer;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.convert.ValueConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Value("${my.page_size}")
    @NumberFormat
    public Integer pageSize;
    private final CustomerDao customerDao;
    private final RoleService roleService;

    public Customer findById(int id){
        return customerDao.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Optional<Customer> optionalCustomer(int id){
        return customerDao.findById(id);
    }

//    public Page<Customer> findCustomer(Optional<String> key, Optional<Integer> pageNumber){
//
//        var pagable = PageRequest.of(0, pageSize);
//        if(pageNumber.isPresent()){
//            pagable = PageRequest.of(pageNumber.get() - 1, pageSize);
//        }
//
//        if(key.isEmpty()){
//            return customerDao.findAll(pagable);
//        }
//        try{
//            var id = Integer.parseInt(key.get());
//            return optionalCustomer(id)
//                    .map(customer -> new PageImpl<>(List.of(customer)))
//                    .orElseGet(() -> new PageImpl<>(Collections.emptyList()));
//        }catch (NumberFormatException e){
//            var word = "%".concat(key.get() + "%").toLowerCase();
//            Specification<Customer>  name = (root, query, cb) -> cb.like(cb.lower(root.get("username")), word);
//            Specification<Customer> email = (root, query, cb) -> cb.like(cb.lower(root.get("email")), word);
//            return customerDao.findAll(Specification.anyOf(name, email), pagable);
//        }
//    }
public Page<Customer> findCustomer(Optional<String> key, Pageable pageable){

//    var pagable = PageRequest.of(0, pageSize);
//    if(pageNumber.isPresent()){
//        pagable = PageRequest.of(pageNumber.get() - 1, pageSize);
//    }

    if(key.isEmpty()){
        return customerDao.findAll(pageable);
    }
    try{
        var id = Integer.parseInt(key.get());
        return optionalCustomer(id)
                .map(customer -> new PageImpl<>(List.of(customer)))
                .orElseGet(() -> new PageImpl<>(Collections.emptyList()));
    }catch (NumberFormatException e){
        var word = "%".concat(key.get() + "%").toLowerCase();
        Specification<Customer>  name = (root, query, cb) -> cb.like(cb.lower(root.get("username")), word);
        Specification<Customer> email = (root, query, cb) -> cb.like(cb.lower(root.get("email")), word);
        return customerDao.findAll(Specification.anyOf(name, email), pageable);
    }
}

    public Customer save(Customer customer){
        return customerDao.save(customer);
    }

    public boolean delete(int id){
        if(customerDao.existsById(id)){
            var c = findById(id);
            c.setActive(false);
            customerDao.saveAndFlush(c);
            return true;
        }
        return false;
    }

    public Customer update(Customer customer,int id){
        var exists = findById(id);
        BeanUtils.copyProperties(customer, exists, "id", "password");
        return customerDao.saveAndFlush(exists);
    }
}
