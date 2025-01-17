package com.nicollasprado.ecommerceAPI.models.dto;

import com.nicollasprado.ecommerceAPI.models.enums.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
