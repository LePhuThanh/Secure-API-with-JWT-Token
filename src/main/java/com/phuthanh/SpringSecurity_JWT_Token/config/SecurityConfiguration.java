package com.phuthanh.SpringSecurity_JWT_Token.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//SecurityConfiguration class determine how to the application handle authen & author
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public final SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                //disable csrf: Cross-Site Request Forgery //To allow POST and PUT requests without CSRF tokens
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                .requestMatchers("")
                    .permitAll()
                .anyRequest().authenticated()
                )

                // Customize authenticationProvider by encoder password //SS use authenticationProvider to authenticate users
                .authenticationProvider(authenticationProvider)
                //To handle the session creation
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //jwtAuthFilter is customized filter, we want to run first jwtAuthFilter then UsernamePasswordAuthenticationFilter (default of Spring Security)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
