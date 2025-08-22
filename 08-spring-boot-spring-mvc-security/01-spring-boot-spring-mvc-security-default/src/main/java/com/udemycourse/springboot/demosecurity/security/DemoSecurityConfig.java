package com.udemycourse.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails ember = User.builder()
                .username("ember")
                .password("{noop}pass")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}pass")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails adam = User.builder()
                .username("adam")
                .password("{noop}pass")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ember, mary, adam);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                                form
                                    .loginPage("/showMyLoginPage")
                                    .loginProcessingUrl("/authenticateUser")
                                    .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

}
