package com.nicollasprado.ecommerceAPI.services;

import com.nicollasprado.ecommerceAPI.models.Cart;
import com.nicollasprado.ecommerceAPI.models.User;
import com.nicollasprado.ecommerceAPI.repositories.CartRepository;
import com.nicollasprado.ecommerceAPI.repositories.ProductRepository;
import com.nicollasprado.ecommerceAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired // Injeç�o de depend�ncia, criando um construtor para eles
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public User findById(Long id){
        // Faz com que n�o entre um dado NULL, retorna apenas vazio
        // Utilizaremos aqui pois caso seja feita uma consulta de um ID que n�o existe no banco ele n�o retornar� erro
        Optional<User> userObj = this.userRepository.findById(id);
        return userObj.orElseThrow(() -> new RuntimeException("Usuario nao encontrado! ID: " + id));
        // .orElseThrow serve para caso o objeto n�o exista (Pois colocamos Optional) ele retornar uma exceç�o
    }

    // Para todas mudanças no banco de dados, nao e bom utilizar em find pois ele cria uma conexao com o banco e salva alguns dados, podendo pesar a aplicacao
    @Transactional
    public User create(User userObj){
        // Para caso o usuario mande um objeto com id
        userObj.setId(null);
        userObj = this.userRepository.save(userObj);
        Cart userCart = new Cart(userObj);
        this.cartRepository.save(userCart);
        return userObj;
    }

    @Transactional
    public User update(User userObj){
        // Verifica se o usuario existe
        User newUserObj = findById(userObj.getId());
        // Estamos permitindo e editando apenas a senha
        newUserObj.setPassword(userObj.getPassword());
        return this.userRepository.save(newUserObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        // Ao realizar delete de entidades associadas a outras entidades pode dar erro, logo vamos trata-lo
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Esvazie seu carrinho antes de excluir o usuario");
        }
    }
}
