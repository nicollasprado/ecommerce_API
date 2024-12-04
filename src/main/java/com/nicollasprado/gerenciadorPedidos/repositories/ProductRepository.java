package com.nicollasprado.gerenciadorPedidos.repositories;

import com.nicollasprado.gerenciadorPedidos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
//    List<Product> findByUser_Id(Long id);

//    busca no banco de dados por meio de Codigo JPQL
//    @Query(value = "SELECT p FROM Product p WHERE p.usersCarts.id = :id")

//    busca por meio de sql nativo
    @Query(value = "SELECT * FROM user_cart i WHERE i.users_carts_id = :id", nativeQuery = true)
//    @Param("id") define que o Long id sera um parametro chamado id
    List<Product> findByUser_Id(@Param("id") Long id);
}
