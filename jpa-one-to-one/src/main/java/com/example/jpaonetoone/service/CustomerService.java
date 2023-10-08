package com.example.jpaonetoone.service;

import com.example.jpaonetoone.dao.AddressDao;
import com.example.jpaonetoone.dao.CustomerDao;
import com.example.jpaonetoone.ds.Address;
import com.example.jpaonetoone.ds.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final AddressDao addressDao;

    public CustomerService(CustomerDao customerDao, AddressDao addressDao) {
        this.customerDao = customerDao;
        this.addressDao = addressDao;
    }
    @Transactional
    public void createDb(){
        Customer customer1=new Customer("John","Doe","john@gmail.com");
        Customer customer2=new Customer("Thomas","Hardy","thomas@gmail.com");
        Customer customer3=new Customer("Charles","Dickens","charles@gmail.com");

        Address address1=new Address("Love Lane","Thanlyin");
        Address address2=new Address("Dream Land","Mandalay");
        Address address3=new Address("79 Park Avenue","Yangon");

        customer1.addAddress(address1);
        customer2.addAddress(address2);
        customer3.addAddress(address3);

        customerDao.save(customer1);
        customerDao.save(customer2);
        customerDao.save(customer3);
    }




}
