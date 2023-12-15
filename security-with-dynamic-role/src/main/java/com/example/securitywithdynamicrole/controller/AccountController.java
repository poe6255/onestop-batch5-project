package com.example.securitywithdynamicrole.controller;

import com.example.securitywithdynamicrole.entity.account.Employee;
import com.example.securitywithdynamicrole.service.account.EmployeeService;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private static final String INFO_PAGE = "account/info";

    @GetMapping("/info")
    public String accountInfo(Model model, @ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("accessUrl", accessUrl);
        return INFO_PAGE;
    }
}
