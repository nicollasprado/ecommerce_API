package com.nicollasprado.gerenciadorPedidos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Product.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    public interface CreateProduct {}
    public interface UpdateProduct {}

    public static final String TABLE_NAME = "Product";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
    @NotEmpty(groups = {CreateProduct.class, UpdateProduct.class})
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 2, nullable = false)
    @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
    private Double price;

    @Column(name = "quantity", nullable = false)
    @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
    private Long quantity;

    @OneToMany(mappedBy = "product")
    private List<CartProducts> cartProducts = new ArrayList<CartProducts>();

    public Product(String name, String description, Double price, Long quantity){
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
