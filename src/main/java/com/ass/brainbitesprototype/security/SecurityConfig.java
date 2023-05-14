package com.ass.brainbitesprototype.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // hash passwords before entering them into database
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Filter requests and ensure users are properly authenticated.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // TODO enable later??
                // Allow anyone regardless of being logged in or not to access these endpoints.
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/register", "/register/**", "/", "/index", "/css/**", "/js/**")
                        .permitAll().anyRequest().authenticated()
                )
                // Handle logging in of a user.
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/sets")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return http.build();
    }

    // Load user details from the database.
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        // Also specifies which encoder to use to encrypt the passwords.
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
