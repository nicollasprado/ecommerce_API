package com.nicollasprado.gerenciadorPedidos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
    @NotEmpty(groups = {CreateProduct.class, UpdateProduct.class})
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 2, nullable = false)
    @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
    @NotEmpty(groups = {CreateProduct.class, UpdateProduct.class})
    @Size(groups = {CreateProduct.class, UpdateProduct.class}, min = 0)
    private double price;

    @Column(name = "quantity", nullable = false)
    @NotNull(groups = {CreateProduct.class, UpdateProduct.class})
    @NotEmpty(groups = {CreateProduct.class, UpdateProduct.class})
    private long quantity;

    @ManyToMany(mappedBy = "cart")
    private List<User> usersCarts = new ArrayList<User>();

    public Product(String name, String description, double price, long quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
}
