package com.nicollasprado.gerenciadorPedidos.services;

import com.nicollasprado.gerenciadorPedidos.models.Cart;
import com.nicollasprado.gerenciadorPedidos.models.CartProducts;
import com.nicollasprado.gerenciadorPedidos.models.Product;
import com.nicollasprado.gerenciadorPedidos.models.User;
import com.nicollasprado.gerenciadorPedidos.repositories.CartProductsRepository;
import com.nicollasprado.gerenciadorPedidos.repositories.CartRepository;
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
            // ARRUMAR ESSA QUEST�O DO ID E FORMA DE BUSCA DO OBJETO CART PRODUCT
            cartProduct = this.cartProductService.findById((userId.getId() + 1L));
            int oldQuantity = cartProduct.getQuantity();
            cartProduct.setQuantity((oldQuantity + quantity));
        }else{
            cartProduct = new CartProducts(userId, productId, quantity);
        }
        this.cartProductsRepository.saveAndFlush(cartProduct);
    }

    @Transactional
    public void removeProductFromCart(Cart userId, Product productId){
        this.cartProductsRepository.removeFromCartByIds(userId.getId(), productId.getId());
    }
}
