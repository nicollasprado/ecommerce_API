package com.nicollasprado.ecommerceAPI.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Desativa a config padrao do spring security e define que as configuraçoes serao nesse arquivo
@EnableMethodSecurity(prePostEnabled = true) // Indica para as classes que aqui sera garantido segurança global antes de acontecer qualquer coisa
public class SecurityConfig {

    // Define as rotas publicar
    private static final String[] PUBLIC_MATCHERS = {
            "/"
    };

    // Define as rotas publicas para post
    private static final String[] PUBLIC_MATCHERS_POST = {
            "/auth/login"
    };

    private static final String[] ADMIN_MATCHERS_POST = {
            "/product"
    };

    @Bean // Cria um objeto a partir de um metodo
    // http sera a requisicao
    // Essa classe serve para realizar as filtragens de permissoes do usuario
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                // define a politica de criaçao de sessoes como stateless, ou seja, o spring security nao mantera o estado das sessoes entre as requisicoes
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // DEV ONLY, remover depois dos testes
                        .requestMatchers(HttpMethod.POST, ADMIN_MATCHERS_POST).hasRole("ADMIN") // libera os endpoints listados para quem tem role admin
                        .anyRequest().authenticated() // para qualquer outro request so tem que estar autenticado
                )
                .build();
    }

    @Bean
    // Essa classe serve para retornar uma instancia de AuthenticationManager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    // Retorna uma instancia de PasswordEncoder
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
