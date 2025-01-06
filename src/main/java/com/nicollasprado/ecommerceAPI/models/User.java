package com.nicollasprado.ecommerceAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

// Entidade = tabela sql
@Entity
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Adiciona listerner de auditing para a entidade (nesse caso utilizado para registrar a data de criacao do user)
public class User {
    public interface CreateUser {}
    public interface UpdateUser {}

    // Definiçao do nome da tabela
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = Os valores ser�o gerados pela coluna de auto incremento do banco, logo, cada registro no
    // banco ser� um novo valor
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    // Groups faz com que ao executar, por exemplo, o CreateUser, ele verifique se a entrada � nula ou vazia.
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 2, max = 100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Torna a senha WRITE ONLY (apenas escrita), aumentando a segurança
    @Column(name = "password", length = 50, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 50)
    private String password;

    @Column(name = "creation_date", insertable = false, updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // Cascade define como as operaç�es feitas nas entidades repercutirao nas entidades associadas
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // Esse override no equals serve para editarmos a forma de comparar se dois objetos instanciados s�o iguais
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    // Geraç�o de c�digo hash para o objeto
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
