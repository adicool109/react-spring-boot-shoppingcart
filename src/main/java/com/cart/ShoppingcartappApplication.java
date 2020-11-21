package com.cart;

import java.net.URL;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cart.model.Cart;
import com.cart.model.Product;
import com.cart.model.User;
import com.cart.services.CartService;
import com.cart.services.ProductService;
import com.cart.services.UserService;

@SpringBootApplication
public class ShoppingcartappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartappApplication.class, args);
	}
	
	@Bean
    CommandLineRunner runner(ProductService productService, CartService cartService, UserService userSvc) {
		String url = "http://placehold.it/200x150";
		//String imageUrl = this.getClass().getResource("/images/beer.jpeg").toString();
        return args -> {
        	
            productService.save(new Product(1L, "TV Set", 300.00f, url));
            productService.save(new Product(2L, "Game Console", 200.00f, url));
            productService.save(new Product(3L, "Sofa", 100.00f, url));
            productService.save(new Product(4L, "Icecream", 5.00f, url));
            productService.save(new Product(5L, "Beer", 3.00f, url));
            productService.save(new Product(6L, "Phone", 500.00f, url));
            productService.save(new Product(7L, "Watch", 30.00f, url));
            
            // create default user
            User user = new User("admin", "Admin User", "password");
            userSvc.createUser(user);
            
        	// create a cart
        	//Cart cart = new Cart(LocalDate.now(),"CREATED",1);
        	// save the cart
        	//cartService.update(cart);
            
//            productService.save(new Product(1L, "TV Set", 300.00f, "http://placehold.it/200x100", cart));
//            productService.save(new Product(2L, "Game Console", 200.00f, "http://placehold.it/200x100", cart));
        };
    }
}
