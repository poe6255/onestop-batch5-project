package com.example.securitywithdynamicrole.Dao.account.role;

import com.example.securitywithdynamicrole.entity.account.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Query("select ur.url from Role r join r.urls ur where r.name = ?1")
    List<String> findUrlNameByName(String name);

    List<Role> findByIdGreaterThan(int i);

    @Query(value = "delete from url_roles where roles.id = ?1", nativeQuery = true)
    void deleteRoleUrlsById(int id);

    boolean existsByName(String roleName);
}
