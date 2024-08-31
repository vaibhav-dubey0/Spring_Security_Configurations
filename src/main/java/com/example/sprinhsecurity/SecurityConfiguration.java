package com.example.sprinhsecurity;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;




    @Configuration
    public class SecurityConfiguration {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/admin").hasRole("ADMIN") // Ensure role is prefixed with ROLE_
                    .requestMatchers("/emp").hasRole("EMP") // Ensure role is prefixed with ROLE_
                    .requestMatchers("/user").permitAll()
                    .anyRequest().authenticated())
                .formLogin(withDefaults());
    
            return httpSecurity.build();
        }
    
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    
        @Bean
        public UserDetailsService userDetailsService() {

            UserDetails adminUser = User
                    .withUsername("Vaibhav")
                    .password(passwordEncoder().encode("Vaibhav"))
                    .roles("ADMIN")
                    .build();
    
            UserDetails empUser = User
                    .withUsername("Nikhil")
                    .password(passwordEncoder().encode("Nikhil"))
                    .roles("EMP")
                    .build();
    
            return new InMemoryUserDetailsManager(adminUser, empUser);
        }


    }
    
    

