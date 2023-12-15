package com.example.securitywithdynamicrole.Dao.account.role;

import com.example.securitywithdynamicrole.entity.account.role.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlDao extends JpaRepository<Url, Integer> {
    Optional<Url> findByName(String name);

    List<Url> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String k);
}
