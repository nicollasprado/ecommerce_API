package com.nicollasprado.ecommerceAPI.repositories;

import com.nicollasprado.ecommerceAPI.models.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductsRepository extends JpaRepository<CartProducts, Long> {
    @Query(value = "SELECT product_id FROM cart_products cp WHERE cp.user_id = :userId AND cp.product_id = :productId LIMIT 1", nativeQuery = true)
    Long getProductIdByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query(value = "SELECT transaction_id FROM cart_products cp WHERE cp.user_id = :userId AND cp.product_id = :productId LIMIT 1", nativeQuery = true)
    Long getTransactionIdByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query(value = "SELECT quantity FROM cart_products cp WHERE cp.user_id = :userId AND cp.product_id = :productId LIMIT 1", nativeQuery = true)
    int getQuantityByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
