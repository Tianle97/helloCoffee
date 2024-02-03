/*
 * Author: Tianle Shu
 * Date: 23/01/24
 */
package com.tus.restbucks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestBucksApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBucksApplication.class, args);
		System.out.println("Server is running successfully!");
	}

}
