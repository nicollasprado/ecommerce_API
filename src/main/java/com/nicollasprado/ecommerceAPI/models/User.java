package com.nicollasprado.ecommerceAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nicollasprado.ecommerceAPI.models.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

// Entidade = tabela sql
@Entity
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Adiciona listerner de auditing para a entidade (nesse caso utilizado para registrar a data de criacao do user)
// UserDetails serve pra identificar classe que sera autenticado via spring security
public class User implements UserDetails {
    public interface CreateUser {}
    public interface UpdateUser {}

    // Definiçao do nome da tabela
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = Os valores ser�o gerados pela coluna de auto incremento do banco, logo, cada registro no
    // banco sera um novo valor
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    // Groups faz com que ao executar, por exemplo, o CreateUser, ele verifique se a entrada � nula ou vazia.
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 2, max = 100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Torna a senha WRITE ONLY (apenas escrita), aumentando a segurança
    @Column(name = "password", nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 50)
    private String password;

    @CreatedDate
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "role", nullable = false)
    private UserRole role;

    // Cascade define como as operaç�es feitas nas entidades repercutirao nas entidades associadas
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = UserRole.USER;
    }

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    // Spring security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
