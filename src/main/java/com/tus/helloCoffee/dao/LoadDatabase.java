package com.tus.helloCoffee.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tus.helloCoffee.dto.Coffee;
import com.tus.helloCoffee.dto.Order;
import com.tus.helloCoffee.dto.User;

@Configuration
public class LoadDatabase {

//    @Bean
//    CommandLineRunner initUserDatabase(UserRepository userRepository) {
//        return args -> {
//            userRepository.save(new User("user", "password", "customer"));
//            userRepository.save(new User("admin", "admin", "employee"));
//        };
//    }
//
//    @Bean
//    CommandLineRunner initOrders(OrderRepository orderRepository,
//    CoffeeRepository coffeeRepository) {
//    return args -> {
//    Order order = new Order("user", "Pending", "Cash", 3.5);
//
//    Coffee co1 = new Coffee("Cappuccino", "Cappuccino.jpg", "Large", "Regular",
//    "Almond milk",
//    "Chocolate powder");
//    Coffee co2 = new Coffee("Espresso", "Espresso.jpg", "Small", "Regular", "No milk", "No extras");
//
//    // Set the Order on the Coffee entities
//    co1.setOrder(order);
//    co2.setOrder(order);
//
//    // Add the Coffee entities to the Order
//    order.getCoffees().add(co1);
//    order.getCoffees().add(co2);
//
//    // Save the Order
//    orderRepository.save(order);
//    };
//    }
}
