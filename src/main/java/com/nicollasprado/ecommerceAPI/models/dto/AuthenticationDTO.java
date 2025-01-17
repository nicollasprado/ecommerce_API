package com.nicollasprado.ecommerceAPI.models.dto;

// Record e um tipo de classe IMUTAVEL que serve para armazenar dados
// Imutavel, logo tudo e final
// Ja tem metodos como equals, hashcode, tostring e construtor feitos pelo compilador
// Nao pode ser extendido
public record AuthenticationDTO(String username, String password) {
}
