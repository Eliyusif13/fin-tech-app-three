package com.sadiqov.tech_app_three.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService){
        try {
            return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).
                    userDetailsService(userDetailsService).
                    passwordEncoder(passwordEncoder).and().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // CORS configuration source: allow dev origins and the custom 'token' header
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        // Allow specific development origins. Change or externalize for production.
        config.setAllowedOrigins(Arrays.asList("http://localhost:63342", "http://127.0.0.1:63342", "http://localhost:8080"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        // Ensure the frontend can read this header if backend sends it as response header
        config.setExposedHeaders(Arrays.asList("token"));
        // If you need cookies/credentials, set to true and ensure AllowedOrigins are explicit (not "*")
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        try {

            httpSecurity.cors().configurationSource(corsConfigurationSource());

            httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

            return httpSecurity.
                    csrf().
                    disable()
                    .authorizeRequests()
                    .antMatchers("/api/v1/register","/api/v1/login","/api/v1/currency","/api/v1/")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and().headers()
                    .and().formLogin().disable().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
