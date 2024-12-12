package com.nicollasprado.ecommerceAPI.services;

import com.nicollasprado.ecommerceAPI.models.Cart;
import com.nicollasprado.ecommerceAPI.models.CartProducts;
import com.nicollasprado.ecommerceAPI.models.Product;
import com.nicollasprado.ecommerceAPI.models.User;
import com.nicollasprado.ecommerceAPI.repositories.CartProductsRepository;
import com.nicollasprado.ecommerceAPI.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductsRepository cartProductsRepository;

    @Autowired
    private CartProductService cartProductService;



    public List<Product> findCartByUserId(Long userId){
        return this.cartRepository.findProductsByUser_Id(userId);
    }

    public List<User> findUserByProductId(Long productId){
        return this.cartRepository.findUserByProduct_Id(productId);
    }

    public Cart findById(Long cartId){
        Optional<Cart> cart = this.cartRepository.findById(cartId);
        return cart.orElseThrow(()-> new RuntimeException("Usuario de ID " + cartId + " nao encontrado"));
    }



    @Transactional
    public void addProductToCart(Cart userId, Product productId, int quantity){
        CartProducts cartProduct;
        if(this.cartProductsRepository.getProductIdByUserIdAndProductId(userId.getId(), productId.getId()) != null){
            Long transactionId = this.cartProductService.getTransactionId(userId.getId(), productId.getId());
            cartProduct = this.cartProductService.findById(transactionId);
            int oldQuantity = cartProduct.getQuantity();
            cartProduct.setQuantity((oldQuantity + quantity));
        }else{
            cartProduct = new CartProducts(userId, productId, quantity);
        }
        this.cartProductsRepository.saveAndFlush(cartProduct);
    }

    @Transactional
    public void decreaseProductFromCart(Cart userId, Product productId, int quantity){
        if(this.cartProductsRepository.getProductIdByUserIdAndProductId(userId.getId(), productId.getId()) != null){
            Long transactionId = this.cartProductService.getTransactionId(userId.getId(), productId.getId());
            CartProducts cartProduct = this.cartProductService.findById(transactionId);
            int oldQuantity = cartProduct.getQuantity();
            if(oldQuantity < quantity){
                throw new RuntimeException("A quantidade a ser diminuida é menor do que a atual do carrinho");
            }else {
                cartProduct.setQuantity((oldQuantity - quantity));
                this.cartProductsRepository.saveAndFlush(cartProduct);
            }
        }else{
            throw new RuntimeException("O usuario de ID " + userId.getId() + " nao esta com o produto ID " + productId.getId() + " no carrinho!");
        }
    }

    @Transactional
    public void removeProductFromCart(Cart userId, Product productId){
        Long transactionId = this.cartProductService.getTransactionId(userId.getId(), productId.getId());
        CartProducts cartProductsObj = this.cartProductService.findById(transactionId);
        this.cartProductsRepository.delete(cartProductsObj);
    }
}
