package com.cart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cart.model.Order;
import com.cart.model.OrderProduct;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Order> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM OrderProduct ordPrd WHERE PRODUCT_ID= :productId")
	void deleteByProductId(@Param("productId") Long productId);
	
	@Transactional
	@Query(value = "SELECT ordPrd.PRODUCT_ID FROM ORDER_PRODUCT ordPrd WHERE ORDER_ID= :orderId", nativeQuery = true)
	Long findByOrderId(@Param("orderId") Long orderId);	// cart.id
	
	@Transactional
	@Query(value = "SELECT ordPrd.ORDER_ID FROM ORDER_PRODUCT ordPrd WHERE PRODUCT_ID= :productId", nativeQuery = true)
	Long findByProductId(@Param("productId") Long productId);	// cart.id
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ORDER_PRODUCT SET QUANTITY= :quantity WHERE ORDER_ID= :orderId", nativeQuery = true)
	void updateQuantity(@Param("orderId") Long orderId, @Param("quantity") int quantity);
}
