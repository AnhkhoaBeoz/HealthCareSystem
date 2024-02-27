package com.khoabeo.quanlyphongkham.security;

import com.khoabeo.quanlyphongkham.jwt.JwtAuthFilter;
import com.khoabeo.quanlyphongkham.jwt.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@EnableWebSecurity
public class ConfigSecurity {
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthFilter jwtAuthFilter;

    public ConfigSecurity(JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthFilter jwtAuthFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthFilter = jwtAuthFilter;
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
            cors.setAllowedMethods(Collections.singletonList("*"));
            cors.setAllowCredentials(true);
            cors.setAllowedHeaders(Collections.singletonList("*"));
            cors.setMaxAge(3600L);
            return cors;
        });

        http.csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(author ->
                {
                    author.requestMatchers(HttpMethod.GET, "/v1/api/doctors/**", "/v1/api/nurses/**").permitAll(); // Cho phép tất cả các phương thức GET của v1/api/doctors
                    author.requestMatchers("/v1/api/roles/**").hasRole("ADMIN"); // Chỉ cho phép ADMIN truy cập các phương thức khác của v1/api/doctors
                    author.requestMatchers("/v1/api/doctors/**").hasRole("ADMIN"); // Chỉ cho phép ADMIN truy cập các phương thức khác của v1/api/doctors
                    author.requestMatchers("/v1/api/nurses/**").hasRole("ADMIN"); // Chỉ cho phép ADMIN truy cập các phương thức khác của v1/api/doctors
                    author.requestMatchers("/v1/api/accounts/**").hasRole("ADMIN");
                    author.requestMatchers("/v1/api/patients/**").hasRole("ADMIN");
                    author.requestMatchers("/v1/api/auth/**").permitAll();
                    author.requestMatchers(HttpMethod.GET, "/v1/api/dutyschedules/**").hasAnyRole("DOCTOR", "NURSE","ADMIN");
                    author.requestMatchers("/v1/api/dutyschedules/**").hasRole("ADMIN");
                    author.requestMatchers("/v1/api/appointment/register").hasRole("PATIENT");
                })
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
