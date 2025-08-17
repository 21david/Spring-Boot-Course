package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // Add support for JDBC to store users in the database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        // tells Spring Security to use JDBC authentication with our data source
        // It will then look into the database for the 'users' and 'authorities' tables its expecting
        return new JdbcUserDetailsManager(dataSource);
    }

    // Restrict access based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        // Read access
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/employees",
                                "/api/employees/**"  // ** = match all subpaths, in this case an id
                        ).hasRole("EMPLOYEE")  // give only Employees access to GET methods at these paths

                        // Create access
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/employees"
                        ).hasRole("MANAGER")

                        // Update access
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/employees"
                        ).hasRole("MANAGER")

                        // Update access (patch)
                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/employees/**"
                        ).hasRole("MANAGER")

                        // Delete access
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/employees/**"
                        ).hasRole("ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF, not generally required for stateless REST APIS that use POST, PUT, DELETE and/or PATCH
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /*
    // Create users by hardcoding them
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("eric")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails david = User.builder()
                .username("david")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(david, mary, john);
    }*/
}
