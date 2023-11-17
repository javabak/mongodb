package com.example.mongodb.security;

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
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrf -> {}))
                .httpBasic((httpBasic) -> {})
                .authorizeHttpRequests((authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/updateUser/{id}", "/createUser",
                                        "/getAllUsers", "/getUser/{id}")
                                .permitAll()
                                .anyRequest()
                                .authenticated()

                ))
                .formLogin((formLogin ->
                    formLogin
                            .loginPage("/")
                            .defaultSuccessUrl("/getAllUsers")


                ))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User
                .builder()
                .username("username")
                .password(passwordEncoder().encode("pass"))
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}