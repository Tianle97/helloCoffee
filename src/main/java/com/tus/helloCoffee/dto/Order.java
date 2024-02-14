package com.tus.helloCoffee.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders") // Specify the table name in the database
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Column(name = "username")
	private String username;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Coffee> coffees;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "total_amount")
	private double totalAmount;

	// Constructors
	public Order() {
		this.coffees = new ArrayList<Coffee>();
	}

	public Order(String username, String orderStatus, String paymentMethod, double totalAmount) {
		this.username = username;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.totalAmount = totalAmount;
		this.coffees = new ArrayList<Coffee>();
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Coffee> getCoffees() {
		return coffees;
	}

	public void setCoffees(List<Coffee> coffees) {
		this.coffees = coffees;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}