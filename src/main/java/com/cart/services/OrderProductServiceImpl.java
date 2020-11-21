package com.cart.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.model.OrderProduct;
import com.cart.model.Product;
import com.cart.repositories.OrderProductRepository;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {
	
	@PersistenceContext
	private EntityManager em;
	
    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
    
    @Override
	public List<OrderProduct> findAllOrder() {
		return this.orderProductRepository.findAll();
	}
    
    @Override
	public void deleteByProductId(Long productId) {
    	this.orderProductRepository.deleteByProductId(productId);
	}
    
    @Override
	public Long findByOrderId(Long orderId) {
		return this.orderProductRepository.findByOrderId(orderId);
	}
    
    @Override
	public void deleteAll() {
    	this.orderProductRepository.deleteAll();
	}
    
    @Override
	public void update(@NotNull(message = "OrderProduct cannot be null.") @Valid OrderProduct order) {
		this.orderProductRepository.save(order);
	}
    
    @Override
	public void updateQuantity(Long pkId, int quantity) {
		this.orderProductRepository.updateQuantity(pkId, quantity);
	}
    
    @Override
	public Long findByProductId(Long productId) {
		return this.orderProductRepository.findByProductId(productId);
	}
    
	/*
	 * public List<Product> getAllOrderedProducts(Long orderId) { //return
	 * this.orderProductRepository.getAllOrderedProducts(orderId); // List<Object[]>
	 * orderedPrd = em.createQuery( //
	 * "SELECT prd.PRODUCT_ID, prd.PRODUCT_NAME, prd.PRICE, prd.PICTURE_URL FROM Cart cart "
	 * // + "JOIN OrderProduct ordPrd ON ordPrd.Order.order_id = cart.id " // +
	 * "JOIN Product prd ON prd.PRODUCT_ID = ordPrd.PRODUCT_ID " // +
	 * "WHERE  cart.ID=2") // .getResultList(); List<Product> prdList = new
	 * ArrayList<Product>(); Connection con = null; Statement stmt = null; try {
	 * //Registering the HSQLDB JDBC driver
	 * Class.forName("org.hsqldb.jdbc.JDBCDriver"); //Creating the connection with
	 * HSQLDB con = DriverManager.getConnection("jdbc:h2:mem:shoppingcartdb", "sa",
	 * ""); if (con!= null) { System.out.println("Connection created successfully");
	 * stmt = con.createStatement(); String sql =
	 * "SELECT prd.PRODUCT_ID, prd.PRODUCT_NAME, prd.PRICE, prd.PICTURE_URL" +
	 * " FROM CART cart " +
	 * " INNER JOIN ORDER_PRODUCT ordPrd ON ordPrd.ORDER_ID = cart.ID" +
	 * " INNER JOIN PRODUCT prd ON prd.PRODUCT_ID = ordPrd.PRODUCT_ID" +
	 * " WHERE cart.ID=" + String.valueOf(orderId); PreparedStatement pstmt =
	 * con.prepareStatement(sql); //pstmt.setLong(1, 2L); ResultSet rs =
	 * stmt.executeQuery(sql); while(rs.next()) { Product product = new Product();
	 * product.setId(rs.getLong("PRODUCT_ID"));
	 * product.setName(rs.getString("PRODUCT_NAME"));
	 * product.setPrice(rs.getFloat("PRICE"));
	 * product.setPictureUrl(rs.getString("PICTURE_URL")); prdList.add(product); }
	 * 
	 * }else{ System.out.println("Problem with creating connection"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return prdList; }
	 */

}
