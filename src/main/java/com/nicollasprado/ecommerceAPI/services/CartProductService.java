package com.nicollasprado.ecommerceAPI.services;

import com.nicollasprado.ecommerceAPI.models.CartProducts;
import com.nicollasprado.ecommerceAPI.repositories.CartProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private CartProductsRepository cartProductsRepository;

    public CartProducts findById(Long transactionId){
        Optional<CartProducts> cartProduct = this.cartProductsRepository.findById(transactionId);
        return cartProduct.orElseThrow(()-> new RuntimeException("No u_id found"));
    }

    public Long getTransactionId(Long userId, Long productId){
        return cartProductsRepository.getTransactionIdByUserIdAndProductId(userId, productId);
    }
}