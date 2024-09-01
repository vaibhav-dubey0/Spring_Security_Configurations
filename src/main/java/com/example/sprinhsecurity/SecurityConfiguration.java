package com.example.sprinhsecurity;

// import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




    @Configuration
     @EnableWebSecurity
    public class SecurityConfiguration {

        private final CustomUserDetailsService userDetailsService;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    


            // THIS CODE IS USE WHEN WE DEFINE SPECFIC USER OF USER DETAILS HARD CODED

            // http
            //     .csrf(csrf -> csrf.disable())
            //     .authorizeHttpRequests(requests -> requests
            //         .requestMatchers("/admin").hasRole("ADMIN") // Ensure role is prefixed with ROLE_
            //         .requestMatchers("/emp").hasRole("EMP") // Ensure role is prefixed with ROLE_
            //         .requestMatchers("/user").permitAll()
            //         .anyRequest().authenticated())
            //     .formLogin(withDefaults());
    
         


            http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
        }
    
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                       .userDetailsService(userDetailsService)
                       .passwordEncoder(passwordEncoder())
                       .and()
                       .build();
        }


        //  Here we mack user itself to login match request from data base 
    
        // @Bean
        // public UserDetailsService userDetailsService() {

        //     UserDetails adminUser = User
        //             .withUsername("Vaibhav")
        //             .password(passwordEncoder().encode("Vaibhav"))
        //             .roles("ADMIN")
        //             .build();
    
        //     UserDetails empUser = User
        //             .withUsername("Nikhil")
        //             .password(passwordEncoder().encode("Nikhil"))
        //             .roles("EMP")
        //             .build();
    
        //     return new InMemoryUserDetailsManager(adminUser, empUser);
        // }


    }
    
    

