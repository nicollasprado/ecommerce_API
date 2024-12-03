package com.nicollasprado.gerenciadorPedidos.controllers;

import com.nicollasprado.gerenciadorPedidos.models.User;
import com.nicollasprado.gerenciadorPedidos.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated // Indica que tera que realizar validacoes
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    // ResponseEntity serve para tratar o dado que sera enviado para o frontend
    // @PathVariable define que o id que receberemos em uma chamada GET sera a variavel id
    public ResponseEntity<User> findById(@PathVariable Long id){
        User userObj = this.userService.findById(id);
        // .ok fara retornar "200 OK" para a requisicao
        // .body sera o corpo da resposta
        return ResponseEntity.ok().body(userObj);
    }

    @PostMapping
    @Validated(User.CreateUser.class) // Define a interface a ser validada
    // @Valid define o objeto que passara pela validacao
    // @RequestBody define que o userObj vira do body da requisicao
    public ResponseEntity<Void> create(@Valid @RequestBody User userObj){
        this.userService.create(userObj);
        // uri e o endereco para dar GET no usuario que criamos
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User newUserObj, @PathVariable Long id){
        newUserObj.setId(id); // Garantir que o novo objeto tem o mesmo id do que estamos alterando
        this.userService.update(newUserObj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
