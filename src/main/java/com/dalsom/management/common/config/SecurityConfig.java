package com.dalsom.management.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/manager/**").hasAnyRole("ROLE_ADMIN", "ROLE_MANAGER") hasRole, hasAnyRole의 경우 ROLE_이 들어가면 exception이 발생한다
//                .antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MASTER')")
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")q
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login").usernameParameter("loginId")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login-failed")
        ;

        return httpSecurity.build();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
