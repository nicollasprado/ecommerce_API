package com.nicollasprado.ecommerceAPI.controllers;

import com.nicollasprado.ecommerceAPI.models.Product;
import com.nicollasprado.ecommerceAPI.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = this.productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    @Validated(Product.CreateProduct.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Product newProductObj){
        this.productService.create(newProductObj);
        URI getUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProductObj.getId()).toUri();
        return ResponseEntity.created(getUri).build();
    }

    @PutMapping("/{id}")
    @Validated(Product.UpdateProduct.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Product newProductObj, @PathVariable Long id){
        newProductObj.setId(id);
        this.productService.update(newProductObj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
