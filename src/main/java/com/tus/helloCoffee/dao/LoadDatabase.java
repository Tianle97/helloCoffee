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

    // @Bean
    // CommandLineRunner initUserDatabase(UserRepository userRepository) {
    // return args -> {
    // userRepository.save(new User("user", "password", "customer"));
    // userRepository.save(new User("admin", "admin", "employee"));
    // };
    // }

    // @Bean
    // CommandLineRunner initDatabase(CoffeeRepository repository) {
    // return args -> {
    // repository
    // .save(new Coffee("Espresso", "Espresso.jpg", "Small", "Regular", "No milk",
    // "No extras"));
    // repository.save(
    // new Coffee("Latte", "Latte.jpg", "Medium", "Light", "Regular milk", "Caramel
    // syrup"));
    // repository.save(new Coffee("Cappuccino", "Cappuccino.jpg", "Large",
    // "Regular", "Almond milk",
    // "Chocolate powder"));
    // repository
    // .save(new Coffee("Mocha", "Mocha.jpg", "Medium", "Extra", "Soy milk",
    // "Whipped cream"));
    // repository.save(new Coffee("Americano", "Americano.jpg", "Small", "Extra",
    // "Coconut milk",
    // "Cinnamon powder"));
    // repository.save(
    // new Coffee("Macchiato", "Macchiato.jpg", "Large", "Regular", "Oat milk",
    // "Vanilla syrup"));
    // repository.save(new Coffee("Flat White", "FlatWhite.jpg", "Medium", "Light",
    // "Rice milk",
    // "Hazelnut syrup"));
    // repository.save(
    // new Coffee("Cortado", "Cortado.jpg", "Small", "Extra", "Cashew milk",
    // "Caramel sauce"));
    // repository.save(
    // new Coffee("Affogato", "Affogato.jpg", "Large", "Regular", "Hemp milk",
    // "Toasted almonds"));
    // repository.save(new Coffee("Irish Coffee", "IrishCoffee.jpg", "Medium",
    // "Extra", "Pea milk",
    // "Irish whiskey"));
    // };
    // }

    // @Bean
    // CommandLineRunner initOrders(OrderRepository orderRepository,
    // CoffeeRepository coffeeRepository) {
    // return args -> {
    // Order order = new Order("user", "Pending", "Cash", 3.5);

    // Coffee co1 = new Coffee("Cappuccino", "Cappuccino.jpg", "Large", "Regular",
    // "Almond milk",
    // "Chocolate powder");
    // Coffee co2 = new Coffee("Espresso", "Espresso.jpg", "Small", "Regular", "No
    // milk", "No extras");

    // // Set the Order on the Coffee entities
    // co1.setOrder(order);
    // co2.setOrder(order);

    // // Add the Coffee entities to the Order
    // order.getCoffees().add(co1);
    // order.getCoffees().add(co2);

    // // Save the Order
    // orderRepository.save(order);
    // };
    // }
}
