package com.cart;

import com.cart.controllers.CartController;
import com.cart.dto.OrderProductModel;
import com.cart.model.Cart;
import com.cart.model.OrderProduct;
import com.cart.services.CartService;
import com.cart.services.OrderProductService;
import com.cart.services.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class CartControllerTest {

    @InjectMocks
    CartController cartController;

    @Mock
    CartService cartService;

    @Mock
    ProductService productService;

    @Mock
    OrderProductService orderProductService;

    Cart order = new Cart();

    /*@BeforeAll
    public void createCart() {
        LocalDate date = LocalDate.parse("2021-05-28");

        order.setDateCreated(date);
        order.setId(1l);
        order.setQuantity(2);
        order.setStatus("PAID");

        List<OrderProduct> orderProducts = new ArrayList<>();
        orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(order.getId()), order.getQuantity())));
        order.setOrderProducts(orderProducts);

        cartService.create(order);
    }*/

    @Test
    public void testCart() {
        OrderProductModel orderData = new OrderProductModel();
        orderData.setProductId(1l);
        orderData.setProductName("TV Set");
        orderData.setPictureUrl("http://placehold.it/200x150");
        orderData.setPrice(300.00f);
        orderData.setQuantity(2);

        ResponseEntity<Cart> responseEntity = cartController.create(orderData);

        boolean returnVal = false;
        if(responseEntity.getStatusCodeValue()==201) {
            returnVal = true;
        }

        assert(returnVal);
    }
}
