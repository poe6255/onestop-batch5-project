package com.example.securitywithdynamicrole.Dao.address;

import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.address.Township;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TownshipDao extends JpaRepository<Township, Integer> {

    @Query("select t from Township t join State s on t.state.id = s.id where t.name = ?1 and s.id = ?2")
    Optional<Township> findByNameAndStateId(String name, int id);
}
