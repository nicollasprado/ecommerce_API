package com.nicollasprado.ecommerceAPI.controllers;

import com.nicollasprado.ecommerceAPI.Security.TokenService;
import com.nicollasprado.ecommerceAPI.models.Cart;
import com.nicollasprado.ecommerceAPI.models.User;
import com.nicollasprado.ecommerceAPI.models.dto.AuthenticationDTO;
import com.nicollasprado.ecommerceAPI.models.dto.LoginResponseDTO;
import com.nicollasprado.ecommerceAPI.models.dto.RegisterDTO;
import com.nicollasprado.ecommerceAPI.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());
        this.userRepository.save(newUser);

        // uri e o endereco para dar GET no usuario que criamos
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
