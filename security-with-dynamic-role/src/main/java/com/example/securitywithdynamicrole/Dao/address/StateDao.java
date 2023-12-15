package com.example.securitywithdynamicrole.Dao.address;

import com.example.securitywithdynamicrole.entity.address.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateDao extends JpaRepository<State, Integer> {

    Optional<State> findByName(String name);
}
