package com.tus.restbucks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.restbucks.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}