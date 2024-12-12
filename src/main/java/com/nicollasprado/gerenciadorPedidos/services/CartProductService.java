package com.nicollasprado.gerenciadorPedidos.services;

import com.nicollasprado.gerenciadorPedidos.models.Cart;
import com.nicollasprado.gerenciadorPedidos.models.CartProducts;
import com.nicollasprado.gerenciadorPedidos.repositories.CartProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private CartProductsRepository cartProductsRepository;

    public CartProducts findById(Long userId){
        Optional<CartProducts> cartProduct = this.cartProductsRepository.findById(userId);
        return cartProduct.orElseThrow(()-> new RuntimeException("Usuario nao encontrado!"));
    }
}
