package com.nicollasprado.gerenciadorPedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicollasprado.gerenciadorPedidos.models.User;

// Interface pois apenas tem funcionalidades
// Repository � a forma do spring de realizar queries no banco de dados
// Long ao lado de user � o tipo de dado do Identificador
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
