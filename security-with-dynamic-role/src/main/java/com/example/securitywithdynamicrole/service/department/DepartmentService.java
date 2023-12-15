package com.example.securitywithdynamicrole.service.department;

import com.example.securitywithdynamicrole.Dao.department.DepartmentDao;
import com.example.securitywithdynamicrole.entity.department.Department;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;

    public Department save(Department department){
        var exists = departmentDao.findByName(department.getName());
        return exists.orElseGet(() -> departmentDao.save(department));
    }

    public List<Department> departments(Optional<String> key){
        if(key.isEmpty()){
            return departmentDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
        }
        try{
            var id = Integer.parseInt(key.get());
            if(id < 0){
                return departmentDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
            }
            return List.of(findById(id));
        }catch (NumberFormatException e){
            var word = "%".concat(key.get() + "%").toLowerCase();
            Specification<Department> byName = (root, q, cb) -> cb.like(cb.lower(root.get("name")), word);
            return departmentDao.findAll(byName);
        }
    }

    public Department findById(int id) {
        return departmentDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Department update(Department department,int id ){
        var exit = findById(id);
        BeanUtils.copyProperties(department,exit,"id");
        return departmentDao.saveAndFlush(exit);
    }

    public void delete(int id) {
        if (departmentDao.existsById(id)) {
            departmentDao.deleteById(id);

        } else {
            throw new EntityNotFoundException("Department with ID " + id + " not found");
        }
    }

}
