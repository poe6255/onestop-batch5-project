package com.example.securitywithdynamicrole;

import com.example.securitywithdynamicrole.service.account.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class UserAdvice {

    private final RoleService roleService;

    @ModelAttribute(name = "accessUrl")
    public List<String> accessUrl(){
        var context = SecurityContextHolder.getContext();
        final var splitIndex = "ROLE_".length();
        return context.getAuthentication().getAuthorities().stream().map(g -> g.getAuthority().substring(splitIndex))
        .map(roleService::findUrl).flatMap(Collection::stream).distinct().toList();
    }
}
