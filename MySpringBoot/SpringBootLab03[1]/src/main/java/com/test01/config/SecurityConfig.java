package com.test01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig { 
 
    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
        http.csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth 
                    .requestMatchers("/", "/index", "/register", "/css/**", "/h2-console/**","/js/**").permitAll() 
                    .anyRequest().authenticated() 
                ) 
                .formLogin(login -> login 
                    .loginPage("/login") 
                    .defaultSuccessUrl("/home", true) 
                    .permitAll() 
                ) 
                .logout(logout -> logout 
                    .logoutSuccessUrl("/") 
                    .permitAll() 
                ); 
     
            return http.build(); 
        } 
     
        @Bean 
        public PasswordEncoder passwordEncoder() { 
            return new BCryptPasswordEncoder(); 
        } 
    } 