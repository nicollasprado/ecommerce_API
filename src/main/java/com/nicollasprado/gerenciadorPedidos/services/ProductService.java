package com.nicollasprado.gerenciadorPedidos.services;

import com.nicollasprado.gerenciadorPedidos.models.Product;
import com.nicollasprado.gerenciadorPedidos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Product findById(Long id){
        Optional<Product> productObj = this.productRepository.findById(id);
        return productObj.orElseThrow(() -> new RuntimeException("Nenhum produto com ID " + id + " foi encontrado!"));
    }

    @Transactional
    public Product create(Product productObj){
        findById(productObj.getId());
        productObj.setId(null);
        productObj = this.productRepository.save(productObj);
        return productObj;
    }

    @Transactional
    public Product update(Product productObj){
        Product newProductObj = findById(productObj.getId());
        newProductObj.setName(productObj.getName());
        newProductObj.setDescription(productObj.getDescription());
        newProductObj.setPrice(productObj.getPrice());
        newProductObj.setQuantity(productObj.getQuantity());
        newProductObj.setUsersCarts(productObj.getUsersCarts());
        return this.productRepository.save(newProductObj);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            this.productRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Remova este produto dos carrinhos antes de exclui-lo!");
        }
    }
}
