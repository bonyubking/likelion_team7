package com.test01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

       @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
               http
               	.authorizeHttpRequests(
               			auth -> auth
               			.requestMatchers("/","/login","/home","/h2_console/**").permitAll()
               			.requestMatchers("/admin").hasRole("ADMIN") 
               			.requestMatchers("/public").hasRole("PUBLIC")
               			.anyRequest().authenticated()
               			
               			
               			).formLogin(login -> login
               					.loginPage("/login")
               					.permitAll()
               					)
               	.logout(logout -> logout.
               			logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll());
              
               return http.build();
       }

       @Bean
       public UserDetailsService userDetailsService(PasswordEncoder encoder) {
               UserDetails user = User.builder()
                       .username("user")
                       .password(encoder.encode("user123"))
                       .roles("USER")
                       .build();
               
               UserDetails admin = User.builder()
                       .username("admin")
                       .password(encoder.encode("admin123"))
                       .roles("ADMIN")
                       .build();
               
               UserDetails PUBLIC = User.builder()
                       .username("public")
                       .password(encoder.encode("public123"))
                       .roles("PUBLIC")
                       .build();
               
               return new InMemoryUserDetailsManager(admin, user, PUBLIC);
       }
       
       @Bean
       public PasswordEncoder passwordEncoder() {
    	   return new BCryptPasswordEncoder();
       }
}
