package com.example.securitywithdynamicrole.service.address;

import com.example.securitywithdynamicrole.Dao.address.StateDao;
import com.example.securitywithdynamicrole.Dao.address.TownshipDao;
import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.address.Township;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TownshipService {

    private final TownshipDao townshipDao;

    public static List<String> townshipName(List<Township> townships) {
        return townships.stream().map(Township::getName).toList();
    }

    public Township save(Township township, int stateId, State state){
        var exists = townshipDao.findByNameAndStateId(township.getName(), stateId);
        exists.ifPresent(System.out::println);
        return exists.orElseGet(() -> {
            var t = townshipDao.save(township);
            state.addTownship(t);
            return t;
        });
    }

    public List<Township> townships(){
        return townshipDao.findAll();
    }
}
