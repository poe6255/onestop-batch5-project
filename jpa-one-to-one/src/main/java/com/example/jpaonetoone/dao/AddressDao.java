package com.example.jpaonetoone.dao;

import com.example.jpaonetoone.ds.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address,Integer> {
}
