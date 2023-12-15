package com.example.securitywithdynamicrole.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {

    private static final String LOG_IN_PAGE = "login";
    private static final String HOME_PAGE = "home";

    @GetMapping("/login")
    public String login(Principal p){
         if (p !=null){
             return  "redirect:/home";
         }

        return LOG_IN_PAGE;
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return LOG_IN_PAGE;
    }

    @GetMapping("/home")
    public String home(Model model,@ModelAttribute("accessUrl") List<String> accessUrl){
        model.addAttribute("accessUrl", accessUrl);
        return HOME_PAGE;
    }
}
