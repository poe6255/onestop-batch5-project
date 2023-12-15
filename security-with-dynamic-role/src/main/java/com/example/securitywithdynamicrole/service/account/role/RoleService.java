package com.example.securitywithdynamicrole.service.account.role;

import com.example.securitywithdynamicrole.Dao.account.role.RoleDao;
import com.example.securitywithdynamicrole.entity.account.role.Role;
import jakarta.persistence.EntityExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@RequiredArgsConstructor
@Getter
public class RoleService {
    private final RoleDao roleDao;
    private final UrlService urlService;
    private final Map<String, String> url = new HashMap<>();

    public boolean isRoleNameExists(String roleName) {
        return roleDao.existsByName(roleName);
    }
    public Role save(Role role){
        return roleDao.findByName(role.getName()).orElseGet(() -> roleDao.save(role));
    }

    public Role update(Role role){
        var exists = findById(role.getId());
        exists.getUrls().forEach(url -> url.removeRole(exists));
        exists.addAllUrl(role.getUrls());
        return roleDao.saveAndFlush(exists);
    }

    public boolean delete(int id){
        var exists = findById(id);
        if(Objects.nonNull(exists)){
            roleDao.delete(exists);
            return true;
        }
        return false;
    }

    public List<Role> findAll(){
        return roleDao.findAll();
    }

    public List<String> findUrl(String name){
        return roleDao.findUrlNameByName(name);
    }

    public Role findById(int i) {
        return roleDao.findById(i).orElseThrow(EntityExistsException::new);
    }

    public List<Role> findRoles(){
//        return roleDao.findByIdGreaterThan(3);
        return  roleDao.findAll();
    }
    public Role getAdminRole(){
        return findById(1);
    }

    public Role getEmployeeRole(){
        return findById(2);
    }
    public Role getCustomerRole(){
        return findById(3);
    }


    public boolean existsById(int i) {
        return roleDao.existsById(i);
    }
}
