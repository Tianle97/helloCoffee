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
  public ResponseEntity<CustomResponse> createUser(@RequestBody User user) {
    User savedUser = userRepo.findByUsername(user.getUsername());
    if (savedUser == null) {
      userRepo.save(user);
      user.setPassword("");
      CustomResponse response = new CustomResponse();
      response.setStatus(HttpStatus.OK.value());
      response.setMessage("User " + user.getUsername() + " created !");
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      CustomResponse response = new CustomResponse();
      response.setStatus(HttpStatus.CONFLICT.value());
      response.setMessage("username " + user.getUsername() + " already exist.");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
  }

  // Create
  @PostMapping("/login")
  public ResponseEntity<CustomResponse> loginUser(@RequestBody User user) {
    User savedUser = userRepo.findByUsername(user.getUsername());
    CustomResponse response = new CustomResponse();
    if (savedUser != null) {
      if (savedUser.getPassword().equals(user.getPassword())) {
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("User " + user.getUsername() + " logged in !");
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage("Wrong username/password for user " + user.getUsername());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
      }
    } else {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setMessage("Wrong username/password for user " + user.getUsername());
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
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
