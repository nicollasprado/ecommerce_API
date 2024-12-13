package com.nicollasprado.ecommerceAPI.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor // Cria construtores para variaveis FINAL
@JsonInclude(JsonInclude.Include.NON_NULL) // So vai aparecer no json o que nao for null
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace; // Nao usar em produçao pois expoe o sistema
    private List<ValidationError> errors;


    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError{
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){ // checa se errors esta vazio
            this.errors = new ArrayList<>();
        }
        this.errors.add(new ValidationError(field, message));
    }
}
