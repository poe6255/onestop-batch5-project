package com.example.jpaonetoone.dao;

import com.example.jpaonetoone.ds.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
}
