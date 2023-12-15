package com.example.securitywithdynamicrole.service.address;

import com.example.securitywithdynamicrole.Dao.address.StateDao;
import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.address.Township;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateDao stateDao;

    public State save(State state){
        var exists = stateDao.findByName(state.getName());
        return exists.orElseGet(() -> stateDao.save(state));
    }

    public List<State> states(Optional<String> key ){
        return stateDao.findAll();
    }
}
