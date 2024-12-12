package com.nicollasprado.gerenciadorPedidos.controllers;

import com.nicollasprado.gerenciadorPedidos.models.Product;
import com.nicollasprado.gerenciadorPedidos.repositories.CartRepository;
import com.nicollasprado.gerenciadorPedidos.services.CartService;
import com.nicollasprado.gerenciadorPedidos.services.ProductService;
import com.nicollasprado.gerenciadorPedidos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Validated
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> findAllByUserId(@PathVariable Long userId){
        List<Product> productsInCart = this.cartService.findCartByUserId(userId);
        return ResponseEntity.ok().body(productsInCart);
    }

    @PutMapping("/{userId}/{productId}/{productQuantity}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable int productQuantity){
        this.cartService.addProductToCart(this.cartService.findById(userId), this.productService.findById(productId), productQuantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId){
        this.cartService.removeProductFromCart(this.cartService.findById(userId), this.productService.findById(productId));
        return ResponseEntity.noContent().build();
    }
}
