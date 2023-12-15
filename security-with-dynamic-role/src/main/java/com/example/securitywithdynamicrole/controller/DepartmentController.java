package com.example.securitywithdynamicrole.controller;

import com.example.securitywithdynamicrole.entity.department.Department;
import com.example.securitywithdynamicrole.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    private static final String CREATE_PAGE = "department/create";
    private static final String LIST_PAGE = "department/list";
    private static final String FIND_LINK = "redirect:/department/find";
    private static  final  String  UPDATE_PAGE = "department/update";


    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("department", new Department());
        model.addAttribute("accessUrl", accessUrl);
        return CREATE_PAGE;
    }

    @PostMapping("/create")
    public String save(@Validated Department department, BindingResult result){
        if(result.hasErrors()){
            return CREATE_PAGE;
        }
        departmentService.save(department);
        return FIND_LINK;
    }

    @GetMapping("/find")
    public String find(Optional<String> key, Model model, @ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("departments", departmentService.departments(key));
        model.addAttribute("accessUrl", accessUrl);
        return LIST_PAGE;
    }

    @GetMapping("/delete")
    public String delete( int id) {
       departmentService.delete(id);
        return LIST_PAGE;
    }
}
