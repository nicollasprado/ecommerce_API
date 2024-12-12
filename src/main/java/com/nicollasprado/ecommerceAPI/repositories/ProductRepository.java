package com.nicollasprado.ecommerceAPI.repositories;

import com.nicollasprado.ecommerceAPI.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
