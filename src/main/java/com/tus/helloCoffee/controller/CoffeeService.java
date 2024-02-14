package com.tus.helloCoffee.controller;

import com.tus.helloCoffee.dto.Coffee;
import com.tus.helloCoffee.dto.Order;
import com.tus.helloCoffee.exceptions.NotFoundException;
import com.tus.helloCoffee.controller.CoffeeService;
import com.tus.helloCoffee.dao.CoffeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/coffees")
public class CoffeeService {

  @Autowired
  CoffeeRepository coffeeRepo;

  // Create
  @PostMapping
  public ResponseEntity createCoffee(@Valid @RequestBody Coffee coffee) {
    Coffee savedCoffee = coffeeRepo.save(coffee);
    return ResponseEntity.status(HttpStatus.OK).body(savedCoffee);
  }

  // // Read
  @GetMapping
  public Iterable<Coffee> getAllCoffees() {
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // String username = authentication.getName();
    // System.out.println(username);
    return coffeeRepo.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Coffee> getCoffeeById(@PathVariable("id") Long id) {
    Optional<Coffee> coffee = coffeeRepo.findById(id);
    if (coffee.isPresent()) {
      return coffee;
    } else {
      throw new NotFoundException("No coffee with id: " + id);
    }
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity updateCoffee(@PathVariable("id") Long id, @RequestBody Coffee coffee) {
    Optional<Coffee> savedCoffee = coffeeRepo.findById(id);
    if (savedCoffee.isPresent()) {
      coffeeRepo.save(coffee);
      // Just return 200 ok response
      return ResponseEntity.status(HttpStatus.OK).body(coffee);
    } else {
      throw new NotFoundException("No coffee with id" + id);
    }
  }

  // Delete
  @DeleteMapping("/{id}")
  public void deleteCoffeeById(@PathVariable Long id) {
    Optional<Coffee> coffee = coffeeRepo.findById(id);
    if (coffee.isPresent()) {
      Coffee existingCoffee = coffee.get();
      coffeeRepo.delete(existingCoffee);
    } else {
      throw new NotFoundException("No coffee with id: " + id);
    }
  }

  @RequestMapping("/name/{type}")
  public ResponseEntity<List<Order>> getCoffeeByType(@PathVariable("type") String type) {
    List<Coffee> CoffeeByType = new ArrayList<>();
    // CoffeesByName = CoffeeRepo.findByName(name); this is exact match
    CoffeeByType = coffeeRepo.findByType(type);
    if (CoffeeByType.size() > 0) {
      return new ResponseEntity(CoffeeByType, HttpStatus.OK);
    } else {
      return new ResponseEntity(CoffeeByType, HttpStatus.NO_CONTENT);
    }
  }
}
