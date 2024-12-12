package com.fatma.getway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 1. Désactivation de CSRF
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**").permitAll()  // 2. Autorisation libre pour les requêtes vers /eureka/**
                        .anyExchange().authenticated()         // 3. Authentification requise pour toutes les autres requêtes
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())); // 4. Configuration du serveur OAuth2 avec JWT

        return serverHttpSecurity.build(); // 5. Retourne la configuration
    }
}
