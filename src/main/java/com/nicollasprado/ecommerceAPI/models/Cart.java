package com.nicollasprado.ecommerceAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Cart.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart {
    public static final String TABLE_NAME = "cart";
    public interface AddToCart {}

    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(groups = {AddToCart.class})
    @NotEmpty(groups = {AddToCart.class})
    @EqualsAndHashCode.Include
    @OneToOne
    @MapsId // Vincula o ID do CARRINHO com o ID do USUARIO ; ID do carrinho sera herdado do id do usuario
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "cart", targetEntity = CartProducts.class, fetch = FetchType.EAGER) // Faz com que ao carregar a entidade carrinho carregue tambem a lista de produtos
    private List<CartProducts> cartProducts = new ArrayList<CartProducts>();

    public Cart(User user){
        this.user = user;
    }
}
