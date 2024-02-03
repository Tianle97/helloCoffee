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
import com.tus.restbucks.exceptions.OrderNotFoundException;

@RestController
public class OrderService {

	@Autowired
	OrderRepository wineRepo;
	// Wine wine;

	// Root / - http://localhost:8081/
	// @GetMapping("/")
	// public String index() {
	// return "<h1>WineCellar Application</h1>";
	// }

	// Get All Wines - http://localhost:8081/wines/
	@GetMapping("/wines")
	public Iterable<Order> getAllWines() {
		return wineRepo.findAll();
	}// End of method

	// Get A wine with id - http://localhost:8081/wines/6
	@GetMapping("/wines/{id}")
	public Optional<Order> getWineById(@PathVariable("id") Long id) {
		Optional<Order> wine = wineRepo.findById(id);
		if (wine.isPresent()) {
			return wine;
		} else {
			throw new OrderNotFoundException("No wine with id: " + id);
		}
	}// End of Find by id method

	// Create a wine - http://localhost:8081/wines/
	@PostMapping("/wines")
	public ResponseEntity createWine(@Valid @RequestBody Order wine) {
		Order savedWine = wineRepo.save(wine);
		return ResponseEntity.status(HttpStatus.OK).body(savedWine);
	}// End of create Method

	// Update wine by id - http://localhost:8081/wines/65
	@PutMapping("wines/{id}")
	public ResponseEntity updateWine(@PathVariable("id") Long id, @RequestBody Order wine) {
		Optional<Order> savedWine = wineRepo.findById(id);
		if (savedWine.isPresent()) {
			wineRepo.save(wine);
			// Just return 200 ok response
			return ResponseEntity.status(HttpStatus.OK).body(wine);
		} else {
			throw new OrderNotFoundException("No wine with id" + id);
		}
	}// End of Update Method

	// Delete a wine by id - http://localhost:8081/wines/66
	@DeleteMapping("/wines/{id}")
	public void deleteWineById(@PathVariable Long id) {
		Optional<Order> wine = wineRepo.findById(id);
		if (wine.isPresent()) {
			Order existingWine = wine.get();
			wineRepo.delete(existingWine);
		} else {
			throw new OrderNotFoundException("No wine with id: " + id);
		}
	}// End of Delete Method

	// Search for a wine using part of its name (Wildcard) -
	@RequestMapping("/wines/name/{name}")
	public ResponseEntity<List<Order>> getWineByName(@PathVariable("name") String name) {
		List<Order> winesByName = new ArrayList<>();
		// winesByName = wineRepo.findByName(name); this is exact match
		winesByName = wineRepo.findByNameContaining(name);
		if (winesByName.size() > 0) {
			return new ResponseEntity(winesByName, HttpStatus.OK);
		} else {
			return new ResponseEntity(winesByName, HttpStatus.NO_CONTENT);
		}
	}// End of Search for a wine using part of its name (Wildcard)

}
