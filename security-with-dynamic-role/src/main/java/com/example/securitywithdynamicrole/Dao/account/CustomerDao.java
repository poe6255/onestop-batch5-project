package com.example.securitywithdynamicrole.Dao.account;

import com.example.securitywithdynamicrole.entity.account.Customer;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface CustomerDao extends JpaRepositoryImplementation<Customer, Integer> {
}
