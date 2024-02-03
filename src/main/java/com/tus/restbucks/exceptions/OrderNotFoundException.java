/*
 * Author: Tianle Shu
 * Date: 23/01/24
 */
package com.tus.restbucks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
	/*
	 * Constructs a new runtime exception with the specified detail message.
	 * *
	 */
	public OrderNotFoundException(String message) {
		super(message);
	}
}
