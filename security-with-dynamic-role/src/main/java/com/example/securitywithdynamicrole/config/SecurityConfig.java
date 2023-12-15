package com.example.securitywithdynamicrole.config;

import com.example.securitywithdynamicrole.config.security.ResetPasswordFilter;
import com.example.securitywithdynamicrole.service.account.AccountService;
import com.example.securitywithdynamicrole.service.account.role.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.server.authorization.AuthorizationWebFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccountService accountService;
    private final ResetPasswordFilter resetPasswordFilter;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(accountService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RoleService roleService) throws Exception {
        http.formLogin(c -> c.loginPage("/login").defaultSuccessUrl("/home")
                        .failureUrl("/login-error").permitAll())
                .logout(c -> c.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll())

                .rememberMe(rememberMe -> {
                    rememberMe
                            .useSecureCookie(true)
                            .rememberMeCookieName("my_spring_remember")
                            .key("my_spring_remember")
                            .rememberMeParameter("remember_me")
                            .userDetailsService(accountService);
                })
                .addFilterBefore(resetPasswordFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.requestMatchers("/bootstrap/**", "/fontawesome/**", "/app/*", "/error", "/", "/favicon.ico", "/reset").permitAll()
                        .requestMatchers("/account/info", "/home").authenticated()
                        .requestMatchers("/reset/**").hasRole("RESET")
                        .requestMatchers("/**").access((authentication, context) -> {
                            var request = context.getRequest().getRequestURI();
                            final var splitIndex = "ROLE_".length();
                            List<String> authorities = authentication.get().getAuthorities().stream().map(g -> g.getAuthority().substring(splitIndex)).toList();
                            if (authorities.isEmpty()) {
                                return new AuthorizationDecision(false);
                            }
                            List<String> accessUrl = authorities.stream().map(roleService::findUrl).flatMap(Collection::stream).toList();
                            if (accessUrl.isEmpty()) {
                                return new AuthorizationDecision(false);
                            } else if (accessUrl.contains(request)) {
                                return new AuthorizationDecision(true);
                            } else if (request.equals("/customer/find") && accessUrl.stream().anyMatch(s -> s.equals("/customer")) ||
                                    request.equals("/employee/find") && accessUrl.stream().anyMatch(s -> s.equals("/employee")) ||
                                    request.equals("/department/find") && accessUrl.stream().anyMatch(s -> s.equals("/department"))) {
                                return new AuthorizationDecision(true);
                            }
                            return new AuthorizationDecision(false);
                        }).anyRequest().authenticated());
        return http.build();
    }

}

