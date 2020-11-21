package com.cart.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy") 
	private LocalDate dateCreated;
	
	private String status;
	
	@Column(nullable = true) 
	private Integer quantity;
	
	//@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	@OneToMany(mappedBy = "pk.order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderProduct> orderProducts = new ArrayList<>();
	
	public List<OrderProduct> getOrderProducts() { return orderProducts; }

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	 
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Cart() {
    }

    public Cart(LocalDate dateCreated,String status,Integer quantity) {
        this.dateCreated = dateCreated;
        this.status = status;
        this.quantity = quantity;
    }
	
}
