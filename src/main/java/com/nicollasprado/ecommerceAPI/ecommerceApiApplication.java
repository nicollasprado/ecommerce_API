package com.nicollasprado.ecommerceAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Ativa o sistema de auditing do JPA (registro de acoes como data de criacao do usuario)
public class ecommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ecommerceApiApplication.class, args);
//		User u = new User("user", "pass");
//		Product p = new Product("product", "", 10.0, 5);
//		UserService uS = new UserService();
//		List<Product> cart = new ArrayList<Product>();
//		cart.add(p);
//		u.setCart(cart);
//		uS.create(u);
	}

}
