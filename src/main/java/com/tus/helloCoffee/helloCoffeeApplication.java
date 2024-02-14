/*
 * Author: Tianle Shu
 * Date: 23/01/24
 */
package com.tus.helloCoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class helloCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(helloCoffeeApplication.class, args);
		System.out.println("Server is running successfully!");
	}

}
