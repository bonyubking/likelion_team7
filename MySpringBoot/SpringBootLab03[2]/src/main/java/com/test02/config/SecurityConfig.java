package com.test02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig { 
 
   @Bean 
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
{ 
         http          
             .authorizeHttpRequests(auth -> auth 
                 .requestMatchers("/", "/index", "/login", "/register").permitAll() 
                 .anyRequest().authenticated() 
             )              
             .oauth2Login(oauth2 -> oauth2 
                 .defaultSuccessUrl("/home", true) 
             ); 
 
         return http.build(); 
     } 
}