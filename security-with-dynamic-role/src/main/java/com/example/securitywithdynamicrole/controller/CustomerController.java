package com.example.securitywithdynamicrole.controller;

import com.example.securitywithdynamicrole.entity.account.Account;
import com.example.securitywithdynamicrole.entity.account.Customer;
import com.example.securitywithdynamicrole.service.account.CustomerService;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import com.example.securitywithdynamicrole.service.address.StateService;
import com.example.securitywithdynamicrole.service.address.TownshipService;
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
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final StateService stateService;
    private final TownshipService townshipService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    private static final String CREATE_PAGE = "customer/create";
    private static final String UPDATE_PAGE = "customer/update";
    private static final String LIST_PATE = "customer/list";
    private static final String FIND_LINK = "redirect:/customer/find";

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("accessUrl") List<String> accessUrl) {
        model.addAttribute("roles",roleService.findRoles());
        model.addAttribute("states", stateService.states(Optional.empty()));
        model.addAttribute("townships", townshipService.townships());
        model.addAttribute("customer", new Customer());
        model.addAttribute("accessUrl", accessUrl);
        return CREATE_PAGE;
    }

    @GetMapping("/update")
    public String update(int id, Model model, @ModelAttribute("accessUrl") List<String> accessUrl) {
        model.addAttribute("roles",roleService.findRoles());
        model.addAttribute("states", stateService.states(Optional.empty()));
        model.addAttribute("townships", townshipService.townships());
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("accessUrl", accessUrl);
        return UPDATE_PAGE;
    }

    @PostMapping("/create")
    @Transactional
    public String create(@Validated({Account.Create.class, Account.Update.class}) Customer customer, BindingResult result,Model model) {
        if (result.hasErrors()) {
            model.addAttribute("states", stateService.states(Optional.empty()));
            model.addAttribute("townships", townshipService.townships());
            return CREATE_PAGE;
        }
        customer.setRole(roleService.getCustomerRole());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.save(customer);
        return FIND_LINK;
    }

    @PostMapping("/update")
    @Transactional
    public String update(@Validated(Account.Update.class) Customer customer, BindingResult result, Model model, @RequestParam int id) {
        if (result.hasErrors()) {
            model.addAttribute("states", stateService.states(Optional.empty()));
            model.addAttribute("townships", townshipService.townships());
            return UPDATE_PAGE;
        }
        customerService.update(customer, id);
        return FIND_LINK;
    }


    @GetMapping("/find")
    public String find(Optional<String> key, Optional<Integer> pageNumber, Model model,@ModelAttribute("accessUrl") List<String> accessUrl) {
        var pagable = PageRequest.of(pageNumber.orElse(0),5);
        var page = customerService.findCustomer(key, pagable);
        model.addAttribute("accessUrl", accessUrl);
        model.addAttribute("customers", page.getContent());
        model.addAttribute("pNums", IntStream.range(1,( page.getTotalPages()-1) + 1).toArray());
        return LIST_PATE;
    }

    @GetMapping("/delete")
    public String delete(Optional<Integer> id, RedirectAttributes model) {
        id.ifPresent(integer -> model.addAttribute("deleted", customerService.delete(integer)));
        return FIND_LINK;
    }
}
