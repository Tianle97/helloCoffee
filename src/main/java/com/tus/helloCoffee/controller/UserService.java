package com.tus.helloCoffee.controller;

import com.tus.helloCoffee.dao.UserRepository;
import com.tus.helloCoffee.dto.Order;
import com.tus.helloCoffee.dto.User;
import com.tus.helloCoffee.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserService {

  @Autowired
  UserRepository userRepo;

  // Create
  @PostMapping("/new")
  public ResponseEntity createUser(@RequestBody User user) {
    User savedUser = userRepo.findByUsername(user.getUsername());
    if (savedUser == null) {
      userRepo.save(user);
      user.setPassword("");
      return ResponseEntity.status(HttpStatus.OK).body("User " + user.getUsername() + " created !");
    } else {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("username " + user.getUsername() + " already exist.");
    }
  }

  // Read
  @GetMapping
  public Iterable<User> getAllUsers() {
    return userRepo.findAll();
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<Order>> getUserByUsername(@PathVariable String username) {
    User user = userRepo.findByUsername(username);

    if (user != null) {
      return new ResponseEntity(user, HttpStatus.OK);
    } else {
      return new ResponseEntity(user, HttpStatus.NO_CONTENT);
    }
  }

  // Update
  @PutMapping("/{username}")
  public ResponseEntity updateUser(@PathVariable String username, @RequestBody User user) {
    User savedUser = userRepo.findByUsername(username);
    if (savedUser != null) {
      userRepo.save(user);
      // Just return 200 ok response
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } else {
      throw new NotFoundException("No user with username" + username);
    }
  }

  // Delete
  @DeleteMapping("/{username}")
  public void deleteUser(@PathVariable String username) {
    User user = userRepo.findByUsername(username);
    if (user != null) {
      userRepo.delete(user);
    } else {
      throw new NotFoundException("No user with username: " + username);
    }
  }
}
