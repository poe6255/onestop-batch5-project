package com.example.securitywithdynamicrole.config.security;

import com.bastiaanjansen.otp.TOTPGenerator;
import com.example.securitywithdynamicrole.controller.ResetPasswordController;
import com.example.securitywithdynamicrole.service.account.AccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ResetPasswordFilter extends OncePerRequestFilter {

    private final AccountService accountService;
    private final TOTPGenerator totpGenerator;
    private final PasswordEncoder encoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var reqUri = request.getRequestURI();
        var uri = request.getRequestURI().split("/");
        if(reqUri.contains("/reset/resetCode/") || reqUri.contains("/reset/change/")){
            var id = 0;
            try{
                id = Integer.parseInt(uri[uri.length -1]);
            }catch (NumberFormatException e){
                System.out.println("can't change id.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
            var account = accountService.findById(id);
            var code = request.getParameter("code");
            System.out.println(code);
            if(!code.isEmpty() && !code.isBlank() && (encoder.matches(account.getUsername(), code) || totpGenerator.verify(code))) {
                var auth = new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_RESET")));
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            }else {
                System.out.println("code does not match");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
