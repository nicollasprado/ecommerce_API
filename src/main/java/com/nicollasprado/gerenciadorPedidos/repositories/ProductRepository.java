package com.nicollasprado.gerenciadorPedidos.repositories;

import com.nicollasprado.gerenciadorPedidos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
