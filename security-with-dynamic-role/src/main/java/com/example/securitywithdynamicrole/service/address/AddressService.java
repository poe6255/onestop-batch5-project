package com.example.securitywithdynamicrole.service.address;

import com.example.securitywithdynamicrole.entity.address.State;
import com.example.securitywithdynamicrole.entity.address.Township;
import com.example.securitywithdynamicrole.service.address.StateService;
import com.example.securitywithdynamicrole.service.address.TownshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final StateService stateService;
    private final TownshipService townshipService;

    public Township save(String state, String township){
        var s = stateService.save(new State(state));
        return townshipService.save(new Township(township), s.getId(), s);
    }

}
