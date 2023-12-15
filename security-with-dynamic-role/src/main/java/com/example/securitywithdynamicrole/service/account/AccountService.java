package com.example.securitywithdynamicrole.service.account;

import com.example.securitywithdynamicrole.Dao.account.AccountDao;
import com.example.securitywithdynamicrole.entity.account.Account;
import com.example.securitywithdynamicrole.entity.account.Customer;
import com.example.securitywithdynamicrole.entity.account.Employee;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import com.example.securitywithdynamicrole.service.account.userDetail.AccountDetail;
import com.example.securitywithdynamicrole.service.address.TownshipService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class AccountService implements UserDetailsService {

    private final AccountDao accountDao;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final TownshipService townshipService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = findByUsername(username);
        if(account.isEmpty()) {
            System.out.println("there is no such " + username);
            throw new UsernameNotFoundException("Admin Not Found");
        }
        System.out.println(account.get().getUsername() + account.get().getPassword());
        return new AccountDetail(account.get());
    }

    public Optional<Account> findByUsername(String name){
        return accountDao.findByUsername(name);
    }

    public void save(Account account){
        accountDao.save(account);
    }

    @Transactional
    public void createAdmin(@Validated Employee employee){
        employeeService.save(employee);
    }

    public Account findById(int id) {
        return accountDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Account findByEmail(String email) {
        return accountDao.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public void savePassword(String password, int id) {
        var account = findById(id);
        account.setPassword(passwordEncoder.encode(password));
        accountDao.saveAndFlush(account);
    }

    public boolean existsAdmin() {
        return roleService.existsById(1);
    }
}
