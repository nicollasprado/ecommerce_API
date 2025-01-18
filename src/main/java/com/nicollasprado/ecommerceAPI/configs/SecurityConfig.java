package com.nicollasprado.ecommerceAPI.configs;

import com.nicollasprado.ecommerceAPI.Security.SecurityFilter;
import com.nicollasprado.ecommerceAPI.models.enums.AdminMatchers;
import com.nicollasprado.ecommerceAPI.models.enums.UserMatchers;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Desativa a config padrao do spring security e define que as configuraçoes serao nesse arquivo
@EnableMethodSecurity(prePostEnabled = true) // Indica para as classes que aqui sera garantido segurança global antes de acontecer qualquer coisa
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    // Define as rotas publicar
    private static final String[] PUBLIC_MATCHERS = {
            "/"
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
                        .requestMatchers(HttpMethod.POST, UserMatchers.getUserPostEndpoints()).hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, UserMatchers.getUserPutEndpoints()).hasRole("USER")
                        .requestMatchers(HttpMethod.GET, UserMatchers.getUserGetEndpoints()).hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, UserMatchers.getUserDeleteEndpoints()).hasRole("USER")
                        .requestMatchers(HttpMethod.POST, AdminMatchers.getAdminPostEndpoints()).hasRole("ADMIN") // libera os endpoints listados para quem tem role admin
                        .requestMatchers(HttpMethod.GET, AdminMatchers.getAdminGetEndpoints()).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, AdminMatchers.getAdminPutEndpoints()).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, AdminMatchers.getAdminDeleteEndpoints()).hasRole("ADMIN")
                        .anyRequest().authenticated() // para qualquer outro request so tem que estar autenticado
                )
                // roda o securityFilter antes do UsernamePasswordAuthenticationFilter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
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
