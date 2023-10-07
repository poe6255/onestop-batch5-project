package com.example.bookshop.service;

import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.ds.CartItem;
import com.example.bookshop.entity.BookItem;
import com.example.bookshop.entity.Customer;
import com.example.bookshop.entity.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
   private  final CustomerDao customerDao;
   private  final PasswordEncoder passwordEncoder;
   private  final CartService cartService;
   @Transactional
      public  void  saveCustomer(Customer customer){
      /* Optional<Customer> customerOptional=customerDao.findByName(customer.getName());
       if (!customerOptional.isPresent()) {
           customer.setPassword(passwordEncoder.encode(customer.getPassword()));
           customer.setRole(Role.USER);
           cartService.listCartItems().stream().forEach(
                   c -> {
                       customer.addBook(toBookItem(c));
                   }
           );
           customerDao.save(customer);
       }
       else {
           Customer customer1=customerOptional.get();
           customer1.setId(customer1.getId());
       }*/
       customer.setPassword(passwordEncoder.encode(customer.getPassword()));
       customer.setRole(Role.USER);
       cartService.listCartItems().stream().forEach(
               c -> {
                   customer.addBook(toBookItem(c));
               }
       );
       customerDao.save(customer);

}

private  BookItem toBookItem(CartItem cartItem){
    return  new BookItem(
            cartItem.getId(),
            cartItem.getTitle(),
            cartItem.getAuthor(),
            cartItem.getPrice(),
            cartItem.getQuantity(),
            cartItem.getPrice() * cartItem.getQuantity()
    );
}


}
