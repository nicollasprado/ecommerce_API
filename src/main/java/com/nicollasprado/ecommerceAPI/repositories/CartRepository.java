package com.nicollasprado.ecommerceAPI.repositories;

import com.nicollasprado.ecommerceAPI.models.Cart;
import com.nicollasprado.ecommerceAPI.models.CartProducts;
import com.nicollasprado.ecommerceAPI.models.Product;
import com.nicollasprado.ecommerceAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //    List<Product> findByUser_Id(Long id);

//    busca no banco de dados por meio de Codigo JPQL
//    @Query(value = "SELECT p FROM Product p WHERE p.usersCarts.id = :id")

    //    busca por meio de sql nativo
    @Query(value = "SELECT * FROM cart_products i WHERE i.user_id = :id", nativeQuery = true)
//    @Param("id") define que o Long id sera um parametro chamado id
    List<Product> findProductsByUser_Id(@Param("id") Long id);

    @Query(value = "SELECT * FROM cart_products i WHERE i.product_id = :id", nativeQuery = true)
    List<User> findUserByProduct_Id(@Param("id") Long id);

    @Query(value = "SELECT * FROM cart_products cp WHERE cp.user_id = :userId AND cp.product_id = :productId LIMIT 1", nativeQuery = true)
    List<CartProducts> findCartProductObjByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
