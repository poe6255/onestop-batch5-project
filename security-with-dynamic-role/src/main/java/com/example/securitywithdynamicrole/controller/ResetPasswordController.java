package com.example.securitywithdynamicrole.controller;

import com.example.securitywithdynamicrole.service.account.AccountService;
import com.example.securitywithdynamicrole.service.account.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reset")
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;
    private final AccountService accountService;
    private final PasswordEncoder encoder;

    @GetMapping
    public String getEmail(){
        return "reset/userMail";
    }

    @PostMapping
    public String getEmail(String email, Model model){
        var account = accountService.findByEmail(email);
        resetPasswordService.sendMail(account);
        model.addAttribute("id", account.getId());
        return "reset/resetCode";
    }

    @PostMapping("/resetCode/{id}")
    public String getCode(@PathVariable int id,Model model){
        var account = accountService.findById(id);
        model.addAttribute("id", account.getId());
        var code = encoder.encode(account.getUsername());
        model.addAttribute("code", code);
        System.out.println(code);
        return "reset/resetPassword";
    }

    @PostMapping("/change/{id}")
    public String change(String password, @PathVariable int id){
        System.out.println("Change password is : " + password);
        accountService.savePassword(password, id);
        return "redirect:/home";
    }
}
