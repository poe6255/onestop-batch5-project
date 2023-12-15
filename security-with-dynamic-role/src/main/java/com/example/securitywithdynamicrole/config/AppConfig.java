package com.example.securitywithdynamicrole.config;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.SecretGenerator;
import com.bastiaanjansen.otp.TOTPGenerator;
import com.example.securitywithdynamicrole.entity.account.Employee;
import com.example.securitywithdynamicrole.entity.account.role.Role;
import com.example.securitywithdynamicrole.entity.account.role.Url;
import com.example.securitywithdynamicrole.service.account.AccountService;
import com.example.securitywithdynamicrole.service.account.EmployeeService;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import com.example.securitywithdynamicrole.service.account.role.UrlService;
import com.example.securitywithdynamicrole.service.address.TownshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.crypto.KeyGenerator;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final RoleService roleService;
    private final AccountService accountService;
    private final UrlService urlService;
    private final PasswordEncoder passwordEncoder;
    private final TownshipService townshipService;

    @EventListener
    @Transactional
    public void handleContextRefresh(ContextRefreshedEvent event) throws NoSuchAlgorithmException, InvalidKeyException {


        event.getApplicationContext().getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class).getHandlerMethods()
                .forEach((key, value) -> {
                    for(var uri : key.getPatternValues()){
                        String[] words = uri.split("/");
                        if(words.length > 1){
                            var k = words[0].concat(String.join("_", Arrays.copyOfRange(words, 1, words.length)));
                            if(k.equals("error")){
                                continue;
                            }
                            roleService.getUrl().put(k, uri);
                            continue;
                        }
                        roleService.getUrl().put(uri, uri);
                    }
                });
        roleService.getUrl().forEach((k, v) -> {
            if(urlService.existsByName(k)){
                urlService.save(new Url(k, v));
            }
        });

        var admin = new Role(1, "ADMIN", urlService.findAll());
        var employee = new Role(2, "EMPLOYEE", List.of(urlService.findByName("home")));
        var customer = new Role(3, "CUSTOMER", List.of(urlService.findByName("home")));

        if(!roleService.existsById(1)){
            roleService.save(admin);
        }
        if(!roleService.existsById(2)){
            roleService.save(employee);
        }
        if(!roleService.existsById(3)){
            roleService.save(customer);
        }

        if(!accountService.existsAdmin()){
            accountService.createAdmin(Employee.admin("admin", passwordEncoder.encode("admin"), roleService.getAdminRole(), townshipService.townships().get(1)));
        }
    }
}
