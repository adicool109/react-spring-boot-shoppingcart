package com.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE CART SET QUANTITY= :quantity WHERE ID= :cartId", nativeQuery = true)
	void updateQuantity(@Param("cartId") Long cartId, @Param("quantity") int quantity);
}
