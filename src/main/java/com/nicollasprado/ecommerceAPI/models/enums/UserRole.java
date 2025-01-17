package com.nicollasprado.ecommerceAPI.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

//    ADMIN(1, "ADMIN"),
//    USER(2, "USER");
//
//    private Integer code;
//    private String description;

//    public static UserRole toEnum(Integer code){
//        if(Objects.isNull(code)) return null;
//
//        for(UserRole i : UserRole.values()){
//            if(code.equals(i.getCode())) return i;
//        }
//
//        throw new IllegalArgumentException("Invalid code: " + code);
//    }
}
