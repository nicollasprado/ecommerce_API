package com.nicollasprado.ecommerceAPI.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMatchers {
    // POST endpoints
    USER_POST("/user"),

    // GET endpoints
    PRODUCT_GET("/product/*"),
    USER_GET("/user/*"),

    // PUT endpoints
    // TODO - Criar metodo de dar permissao de editar APENAS o perfil do usuario que fez a requisicao
    // TODO - para o cart tambem
    USER_PUT("/user/*"),
    CART_PUT("/cart/*"),

    // DELETE endpoints
    // TODO - Criar metodo para deletar apenas a sua propria conta e remover somente seus produtos do carrinho
    USER_DELETE("/user/*"),
    CART_DELETE("/cart/*");


    private final String matcher;

    public static String[] getUserPostEndpoints(){
        return new String[]{USER_POST.getMatcher()};
    }

    public static String[] getUserGetEndpoints(){
        return new String[] {PRODUCT_GET.getMatcher(), USER_GET.getMatcher()};
    }

    public static String[] getUserPutEndpoints(){
        return new String[] {USER_PUT.getMatcher(), CART_PUT.getMatcher()};
    }

    public static String[] getUserDeleteEndpoints(){
        return new String[] {USER_DELETE.getMatcher(), CART_DELETE.getMatcher()};
    }
}
