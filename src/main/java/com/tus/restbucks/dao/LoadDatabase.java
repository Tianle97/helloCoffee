package com.tus.restbucks.dao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tus.restbucks.dto.Coffee;
import com.tus.restbucks.dto.User;

@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initUserDatabase(UserRepository userRepository) {
    return args -> {
      userRepository.save(new User("user", "password", "customer"));
      userRepository.save(new User("admin", "admin", "employee"));
    };
  }

  @Bean
  CommandLineRunner initDatabase(CoffeeRepository repository) {
    return args -> {
      repository
          .save(new Coffee("Espresso", "Espresso.jpg", "Small", "Regular", "No milk", new String[] { "No extras" }));
      repository.save(
          new Coffee("Latte", "Latte.jpg", "Medium", "Light", "Regular milk", new String[] { "Caramel syrup" }));
      repository.save(new Coffee("Cappuccino", "Cappuccino.jpg", "Large", "Regular", "Almond milk",
          new String[] { "Chocolate powder" }));
      repository
          .save(new Coffee("Mocha", "Mocha.jpg", "Medium", "Extra", "Soy milk", new String[] { "Whipped cream" }));
      repository.save(new Coffee("Americano", "Americano.jpg", "Small", "Extra", "Coconut milk",
          new String[] { "Cinnamon powder" }));
      repository.save(
          new Coffee("Macchiato", "Macchiato.jpg", "Large", "Regular", "Oat milk", new String[] { "Vanilla syrup" }));
      repository.save(new Coffee("Flat White", "FlatWhite.jpg", "Medium", "Light", "Rice milk",
          new String[] { "Hazelnut syrup" }));
      repository.save(
          new Coffee("Cortado", "Cortado.jpg", "Small", "Extra", "Cashew milk", new String[] { "Caramel sauce" }));
      repository.save(
          new Coffee("Affogato", "Affogato.jpg", "Large", "Regular", "Hemp milk", new String[] { "Toasted almonds" }));
      repository.save(new Coffee("Irish Coffee", "IrishCoffee.jpg", "Medium", "Extra", "Pea milk",
          new String[] { "Irish whiskey" }));
    };
  }
}
