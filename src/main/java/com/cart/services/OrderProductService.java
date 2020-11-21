package com.cart.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.cart.model.OrderProduct;
import com.cart.model.Product;

@Validated
public interface OrderProductService {

    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);

    //List<Product> getAllOrderedProducts(Long orderId);
    
    void deleteByProductId(Long productId);
    
    List<OrderProduct> findAllOrder();
    
    Long findByOrderId(Long orderId);
    
    Long findByProductId(Long productId);
    
    void deleteAll();
    
    void update(@NotNull(message = "The order cannot be null.") @Valid OrderProduct order);
    
    void updateQuantity(Long pkId, int quantity);
}
