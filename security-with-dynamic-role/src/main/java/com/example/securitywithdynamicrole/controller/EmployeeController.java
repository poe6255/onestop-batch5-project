package com.example.securitywithdynamicrole.controller;

import com.example.securitywithdynamicrole.entity.account.Account;
import com.example.securitywithdynamicrole.entity.account.Employee;
import com.example.securitywithdynamicrole.service.account.EmployeeService;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import com.example.securitywithdynamicrole.service.address.StateService;
import com.example.securitywithdynamicrole.service.address.TownshipService;
import com.example.securitywithdynamicrole.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final StateService stateService;
    private final TownshipService townshipService;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    private static final String CREATE_PAGE = "employee/create";
    private static final String UPDATE_PAGE = "employee/update";
    private static final String LIST_PATE = "employee/list";
    private static final String FIND_LINK = "redirect:/employee/find";

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("accessUrl") List<String> accessUrl) {
        model.addAttribute("roles", roleService.findRoles());
        model.addAttribute("departments", departmentService.departments(Optional.empty()));
        model.addAttribute("states", stateService.states(Optional.empty()));
        model.addAttribute("townships", townshipService.townships());        model.addAttribute("employee", new Employee());
        model.addAttribute("accessUrl", accessUrl);
        return CREATE_PAGE;
    }

    @GetMapping("/update")
    public String update(int id, Model model, @ModelAttribute("accessUrl") List<String> accessUrl) {
        model.addAttribute("roles", roleService.findRoles());
        model.addAttribute("departments", departmentService.departments(Optional.empty()));
        model.addAttribute("states", stateService.states(Optional.empty()));
        model.addAttribute("townships", townshipService.townships());        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("accessUrl", accessUrl);
        return UPDATE_PAGE;

    }

    @PostMapping("/create")
    @Transactional
    public String create(@Validated({Account.Create.class, Account.Update.class}) Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles",roleService.findRoles());
            model.addAttribute("departments", departmentService.departments(Optional.empty()));
            model.addAttribute("states", stateService.states(Optional.empty()));
            model.addAttribute("townships", townshipService.townships());
            return CREATE_PAGE;
        }
     // employee.setRole(roleService.getEmployeeRole());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.save(employee);
        return FIND_LINK;
    }

    @PostMapping("/update")
    @Transactional
    public String update(@Validated(Account.Update.class) Employee employee, BindingResult result, Model model, @RequestParam int id) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.departments(Optional.empty()));
            model.addAttribute("states", stateService.states(Optional.empty()));
            model.addAttribute("townships", townshipService.townships());
            return UPDATE_PAGE;
        }
        employeeService.update(employee, id);
        return FIND_LINK;
    }

    @GetMapping("/find")
    public String find(Optional<String> key, Optional<Integer> pageNumber, Model model, @ModelAttribute("accessUrl") List<String> accessUrl) {
        var pagable = PageRequest.of(pageNumber.orElse(0),5);
        var page = employeeService.findEmployee(key,pagable);
        System.out.println("----------------------------------"+pageNumber);
        model.addAttribute("employees", page.getContent());
        model.addAttribute("size",pagable.getOffset()+1);
        System.out.println(" ========================================================" +pagable.getOffset());
        model.addAttribute("pNums", IntStream.range(0, (page.getTotalPages()-1) + 1).toArray());
        model.addAttribute("accessUrl", accessUrl);
        return LIST_PATE;
    }


    @GetMapping("/delete")
    public String delete(Optional<Integer> id, RedirectAttributes model) {
        id.ifPresent(integer -> model.addAttribute("deleted", employeeService.delete(integer)));
        return FIND_LINK;
    }
}
