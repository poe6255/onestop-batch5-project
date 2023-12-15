package com.example.securitywithdynamicrole.service.account.role;

import com.example.securitywithdynamicrole.Dao.account.role.UrlDao;
import com.example.securitywithdynamicrole.entity.account.role.Url;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlDao urlDao;

    public List<Url> findAll(){
        return urlDao.findAll();
    }

    public Url save(Url url){
        return urlDao.findByName(url.getName()).orElseGet(() -> urlDao.save(url));
    }

    public Url findById(int id){
        return urlDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Url findByName(String name) {
        return urlDao.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public List<Url> findByNameContaining(String name) {
        return urlDao.findByNameContainingIgnoreCase(name);
    }

    public boolean existsByName(String k) {
        return urlDao.existsByName(k);
    }
}
