package com.cart.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cart.dto.OrderProductDto;
import com.cart.dto.OrderProductModel;
import com.cart.exception.ResourceNotFoundException;
import com.cart.model.Cart;
import com.cart.model.OrderProduct;
import com.cart.model.Product;
import com.cart.services.CartService;
import com.cart.services.OrderProductService;
import com.cart.services.ProductService;

@RestController
@RequestMapping("/cart/orders")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderProductService orderProductService;
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Cart> getCartList() {
        return this.cartService.getAllOrders();
    }

	@PostMapping
    public ResponseEntity<Cart> create(@RequestBody OrderProductModel orderData) {
        List<OrderProductDto> formDtos = mapToEntity(orderData);
        Boolean isExistingPrd = validateProductsExistence(formDtos);
        if(!isExistingPrd) {
        	new ResourceNotFoundException("Product not found");
        }
        
        Boolean isPrdOrderPrev = false;
        isPrdOrderPrev = updateQuantityIfExistingOrderedPrd(orderData);
        
        Cart order = new Cart();
        order.setStatus("PAID");
        order.setQuantity(orderData.getQuantity());
        
        if(!isPrdOrderPrev) {
        	order = this.cartService.create(order);

            List<OrderProduct> orderProducts = new ArrayList<>();
            for (OrderProductDto dto : formDtos) {
                orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
                  .getProduct()
                  .getId()), dto.getQuantity())));
            }

            order.setOrderProducts(orderProducts);

            this.cartService.update(order);
        } else {
        	Long cartId = this.orderProductService.findByProductId(orderData.getProductId());
        	this.cartService.updateQuantity(cartId, orderData.getQuantity());
        	order.setId(cartId);
        }
        

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
        
        // delete record incase of zero or -ve quantity value
        if(orderData.getQuantity() < 0 || orderData.getQuantity() == 0) {
        	deleteRecord(order.getId());
        }
        
        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
	
	//@RequestMapping(method = RequestMethod.DELETE, value="/deleteRecord/{cartId}")
	@DeleteMapping(value = "/deleteRecord/{cartId}")
	public void deleteRecord(@PathVariable Long cartId/*, @PathVariable Long productId*/) {
		Long productId = this.orderProductService.findByOrderId(cartId);
		//this.cartService.delete(cartId);
		
		Iterable<Cart> cartInfo = this.cartService.getAllOrders();
		List<Long> cartIdWithSamePrd = new ArrayList<Long>();
		for(Cart cartData: cartInfo) {
			List<OrderProduct> orderPrdList = cartData.getOrderProducts();
			Long cartID = cartData.getId();
			for(OrderProduct ordPrd : orderPrdList) {
				Long prdId = ordPrd.getProduct().getId();
				if(prdId.equals(productId)) {
					cartIdWithSamePrd.add(cartID);
				}
			}
		}
		
		for(Long id: cartIdWithSamePrd) {
			this.cartService.delete(id);
		}
		
		this.orderProductService.deleteByProductId(productId);
	}
	
	@DeleteMapping(value = "/deleteAll")
	public void deleteAllRecord() {
		this.cartService.deleteAll();
		this.orderProductService.deleteAll();
	}
	
	@GetMapping(value="/getOrderedProducts")
	public int getNumberOfOrderedProducts() {
		Iterable<Cart> cartInfo = this.cartService.getAllOrders();
		int productCount = 0;
		for(Cart cartData: cartInfo) {
			productCount = productCount + cartData.getQuantity();
		}
		return productCount;
	}
	
	private List<OrderProductDto> mapToEntity(OrderProductModel orderData) {
		List<OrderProductDto> productOrders = new ArrayList<OrderProductDto>();
		OrderProductDto formDto = new OrderProductDto();
		Product prd = new Product();
		prd.setId(orderData.getProductId());
		prd.setName(orderData.getProductName());
		prd.setPictureUrl(orderData.getPictureUrl());
		prd.setPrice(orderData.getPrice());
		
		int quantity = orderData.getQuantity();
		
		formDto.setQuantity(quantity);
		formDto.setProduct(prd);
		
		productOrders.add(formDto);
		
		return productOrders;
	}

	public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
	
	private Boolean validateProductsExistence(List<OrderProductDto> orderProducts) {
		Boolean isPrdExists = Boolean.TRUE;
        List<OrderProductDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
        	isPrdExists = Boolean.FALSE;
            //new ResourceNotFoundException("Product not found");
        }
        return isPrdExists;
    }
	
	private Boolean updateQuantityIfExistingOrderedPrd(OrderProductModel orderData) {
		//OrderProduct orderProduct = new OrderProduct();
		Boolean isExistingPrdUpdate = false;
		List<OrderProduct> orderPrdsList = this.orderProductService.findAllOrder();
		Long orderedPrdId = orderData.getProductId();
		int orderedQuantity = orderData.getQuantity();
		for(OrderProduct ordPrd : orderPrdsList) {
			Long prdId = ordPrd.getProduct().getId();
			Long orderPrdId = ordPrd.getPk().getOrder().getId();
			
			if(prdId.equals(orderedPrdId)) {
				int currentQuantity = ordPrd.getQuantity();
				int updatedQuantity = currentQuantity + orderedQuantity;
				if(updatedQuantity < 0 || orderedQuantity < 0) {
					updatedQuantity = 0;
				}
				//orderProduct.setQuantity(updatedQuantity);
								
				// update the quantity rather creating a new order request
				this.orderProductService.updateQuantity(orderPrdId, updatedQuantity);
				isExistingPrdUpdate = true;
			}
		}
		
		return isExistingPrdUpdate;
	}
}
