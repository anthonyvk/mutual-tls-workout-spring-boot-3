package com.savk.workout.mutualtlsworkout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/api/**")
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> {
                            try {
                                auth
                                        .requestMatchers( "/secured/**")
                                        .authenticated()
                                        .and()
                                        .x509(Customizer.withDefaults());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .build();
    }
}
