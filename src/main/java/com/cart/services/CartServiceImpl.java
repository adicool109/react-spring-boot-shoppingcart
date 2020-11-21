package com.cart.services;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.model.Cart;
import com.cart.repositories.CartRepository;
import com.cart.repositories.OrderProductRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public @NotNull Iterable<Cart> getAllOrders() {
		return cartRepository.findAll();
	}

	@Override
	public Cart create(@NotNull(message = "The order cannot be null.") @Valid Cart order) {
		order.setDateCreated(LocalDate.now());
        return this.cartRepository.save(order);
	}

	@Override
	public void update(@NotNull(message = "The order cannot be null.") @Valid Cart order) {
		this.cartRepository.save(order);
	}

	@Override
	public void delete(Long cartId) {
		this.cartRepository.deleteById(cartId);
	}

	@Override
	public void deleteAll() {
		this.cartRepository.deleteAll();
	}

	@Override
	public void updateQuantity(Long cartId, int quantity) {
		this.cartRepository.updateQuantity(cartId, quantity);
	}

}
