package com.tus.restbucks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.restbucks.dto.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
  List<Coffee> findByType(String type);
}
