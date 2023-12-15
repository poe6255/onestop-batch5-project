package com.example.securitywithdynamicrole.controller;

import com.example.securitywithdynamicrole.entity.account.role.Role;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import com.example.securitywithdynamicrole.service.account.role.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final UrlService urlService;
    private static final String CREATE_PAGE = "role/create";
    private static final String UPDATE_PAGE = "role/update";
    private static final String LIST_PATE = "role/list";
    private static final String FIND_LINK = "redirect:/role/find";

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("role", new Role());
        model.addAttribute("customer", urlService.findByNameContaining("customer"));;
        model.addAttribute("employee", urlService.findByNameContaining("employee"));
        model.addAttribute("department", urlService.findByNameContaining("department"));
        model.addAttribute("accessUrl", accessUrl);
        return CREATE_PAGE;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("role") @Valid Role role, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("customer", urlService.findByNameContaining("customer"));;
            model.addAttribute("employee", urlService.findByNameContaining("employee"));
            model.addAttribute("department", urlService.findByNameContaining("department"));
            return CREATE_PAGE;
        }
        if (roleService.isRoleNameExists(role.getName())) {
            System.out.println(role.getName());
            model.addAttribute("customer", urlService.findByNameContaining("customer"));;
            model.addAttribute("employee", urlService.findByNameContaining("employee"));
            model.addAttribute("department", urlService.findByNameContaining("department"));
            model.addAttribute("roleNameExistsError", "This role name already exists");
            return CREATE_PAGE;
        }
        role.addAllUrl(role.getUrls());
        roleService.save(role);
        return FIND_LINK;
    }

    @GetMapping("/update")
    public String updateRole(int id, Model model, @ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("role", roleService.findById(id));
        model.addAttribute("customer", urlService.findByNameContaining("customer"));
        model.addAttribute("employee", urlService.findByNameContaining("employee"));
        model.addAttribute("department", urlService.findByNameContaining("department"));
        model.addAttribute("accessUrl", accessUrl);
        return UPDATE_PAGE;
    }

    @PostMapping("/update")
    public String updateRole(@Validated Role role, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("customer", urlService.findByNameContaining("customer"));;
            model.addAttribute("employee", urlService.findByNameContaining("employee"));
            model.addAttribute("department", urlService.findByNameContaining("department"));
            return UPDATE_PAGE;
        }
        roleService.update(role);
        return FIND_LINK;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, Model model){
        model.addAttribute("deleted", roleService.delete(id));
        return FIND_LINK;
    }

    @GetMapping("/find")
    public String find(Model model, @ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("roles", roleService.findRoles());
        model.addAttribute("accessUrl", accessUrl);
        return LIST_PATE;
    }
}
