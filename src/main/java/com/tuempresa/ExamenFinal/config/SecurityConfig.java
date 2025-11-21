package com.tuempresa.tuapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Nota: Aquí solo declaro el encoder. Para un proyecto real debes añadir WebSecurityConfigurer con rutas públicas/privadas y JWT.
}
