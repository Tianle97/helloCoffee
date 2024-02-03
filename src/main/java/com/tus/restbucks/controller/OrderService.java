/*
 * Author: Tianle Shu
 * Date: 23/01/24
 */
package com.tus.restbucks.controller;

import java.util.*;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.restbucks.dao.OrderRepository;
import com.tus.restbucks.dto.Order;
import com.tus.restbucks.exceptions.NotFoundException;

@RestController
@RequestMapping("/orders")
public class OrderService {

	@Autowired
	OrderRepository orderRepo;

	// Get All Orders - http://localhost:8081/
	@GetMapping
	public Iterable<Order> getAllOrders() {
		return orderRepo.findAll();
	}// End of method

	// Get an order with id - http://localhost:8081/6
	@GetMapping("/{id}")
	public Optional<Order> getWineById(@PathVariable("id") Long id) {
		Optional<Order> order = orderRepo.findById(id);
		if (order.isPresent()) {
			return order;
		} else {
			throw new NotFoundException("No order with id: " + id);
		}
	}// End of Find by id method

	// Create a order - http://localhost:8081/
	@PostMapping
	public ResponseEntity createWine(@Valid @RequestBody Order order) {
		Order savedOrder = orderRepo.save(order);
		return ResponseEntity.status(HttpStatus.OK).body(savedOrder);
	}// End of create Method

	// Update order by id - http://localhost:8081/65
	@PutMapping("/{id}")
	public ResponseEntity updateWine(@PathVariable("id") Long id, @RequestBody Order order) {
		Optional<Order> savedOrder = orderRepo.findById(id);
		if (savedOrder.isPresent()) {
			orderRepo.save(order);
			// Just return 200 ok response
			return ResponseEntity.status(HttpStatus.OK).body(order);
		} else {
			throw new NotFoundException("No order with id" + id);
		}
	}// End of Update Method

	// Delete a order by id - http://localhost:8081/66
	@DeleteMapping("/{id}")
	public void deleteWineById(@PathVariable Long id) {
		Optional<Order> order = orderRepo.findById(id);
		if (order.isPresent()) {
			Order existingWine = order.get();
			orderRepo.delete(existingWine);
		} else {
			throw new NotFoundException("No order with id: " + id);
		}
	}// End of Delete Method
}
