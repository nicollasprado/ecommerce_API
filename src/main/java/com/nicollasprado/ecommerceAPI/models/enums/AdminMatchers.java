package com.nicollasprado.ecommerceAPI.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminMatchers {
    // POST endpoints
    PRODUCT_POST("/product"),
    REGISTER_POST("/register"),

    // GET endpoints
    PRODUCT_GET("/product/*"),
    USER_GET("/user/*"),

    // PUT endpoints
    PRODUCT_PUT("/product/*"),
    USER_PUT("/user/*"),
    CART_PUT("/cart/*"),

    // DELETE endpoints
    USER_DELETE("/user/*"),
    PRODUCT_DELETE("/product/*"),
    CART_DELETE("/cart/*");


    private final String matcher;

    public static String[] getAdminPostEndpoints(){
        return new String[]{PRODUCT_POST.getMatcher(), REGISTER_POST.getMatcher()};
    }

    public static String[] getAdminGetEndpoints(){
        return new String[] {PRODUCT_GET.getMatcher(), USER_GET.getMatcher()};
    }

    public static String[] getAdminPutEndpoints(){
        return new String[] {
                PRODUCT_PUT.getMatcher(),
                USER_PUT.getMatcher(),
                CART_PUT.getMatcher()
        };
    }

    public static String[] getAdminDeleteEndpoints(){
        return new String[] {
                PRODUCT_DELETE.getMatcher(),
                USER_DELETE.getMatcher(),
                CART_DELETE.getMatcher()
        };
    }
}
