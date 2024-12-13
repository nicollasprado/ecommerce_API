package com.nicollasprado.ecommerceAPI.services;

import com.nicollasprado.ecommerceAPI.models.CartProducts;
import com.nicollasprado.ecommerceAPI.repositories.CartProductsRepository;
import com.nicollasprado.ecommerceAPI.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private CartProductsRepository cartProductsRepository;

    public CartProducts findById(Long transactionId){
        Optional<CartProducts> cartProduct = this.cartProductsRepository.findById(transactionId);
        return cartProduct.orElseThrow(()-> new ObjectNotFoundException("transaction_id " + transactionId + " not found"));
    }

    public Long getTransactionId(Long userId, Long productId){
        return cartProductsRepository.getTransactionIdByUserIdAndProductId(userId, productId);
    }
}
