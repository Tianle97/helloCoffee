package com.tus.restbucks.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tus.restbucks.dao.CoffeeRepository;
import com.tus.restbucks.dto.Coffee;

@SpringBootTest
@AutoConfigureMockMvc
public class CoffeeServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CoffeeRepository coffeeRepo;

	@Test
	public void testCreateCoffee() throws Exception {
		Coffee coffee = new Coffee("Espresso", "Espresso.jpg", "Small", "Regular", "No milk", "No extras");
		when(coffeeRepo.save(any(Coffee.class))).thenReturn(coffee);

		mockMvc.perform(post("/coffees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(coffee)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.type").value("Espresso"));

		verify(coffeeRepo, times(1)).save(any(Coffee.class));
	}

	@Test
	public void testGetAllCoffees() throws Exception {
		mockMvc.perform(get("/coffees"))
				.andExpect(status().isOk());

		verify(coffeeRepo, times(1)).findAll();
	}

	@Test
	public void testGetCoffeeById() throws Exception {
		Coffee coffee = new Coffee("Latte", "Latte.jpg", "Medium", "Light", "Regular milk", "Caramel syrup");
		when(coffeeRepo.findById(1L)).thenReturn(java.util.Optional.of(coffee));

		mockMvc.perform(get("/coffees/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.type").value("Latte"));
	}

	@Test
	public void testUpdateCoffee() throws Exception {
		Coffee coffee = new Coffee("Latte", "Latte.jpg", "Medium", "Light", "Regular milk", "Caramel syrup");
		when(coffeeRepo.findById(1L)).thenReturn(java.util.Optional.of(coffee));
		when(coffeeRepo.save(any(Coffee.class))).thenReturn(coffee);

		mockMvc.perform(put("/coffees/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(coffee)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.type").value("Latte"));

		verify(coffeeRepo, times(1)).save(any(Coffee.class));
	}

	@Test
	public void testDeleteCoffeeById() throws Exception {
		Coffee coffee = new Coffee("Latte", "Latte.jpg", "Medium", "Light", "Regular milk", "Caramel syrup");
		when(coffeeRepo.findById(1L)).thenReturn(java.util.Optional.of(coffee));

		mockMvc.perform(delete("/coffees/1"))
				.andExpect(status().isOk());

		verify(coffeeRepo, times(1)).delete(any(Coffee.class));
	}

	@Test
	public void testGetCoffeeByIdNotFound() throws Exception {
		when(coffeeRepo.findById(1L)).thenReturn(java.util.Optional.empty());

		mockMvc.perform(get("/coffees/1"))
				.andExpect(status().isNotFound());

		verify(coffeeRepo, times(1)).findById(1L);
	}

	@Test
	public void testUpdateCoffeeNotFound() throws Exception {
		Coffee coffee = new Coffee("Latte", "Latte.jpg", "Medium", "Light", "Regular milk", "Caramel syrup");
		when(coffeeRepo.findById(1L)).thenReturn(java.util.Optional.empty());

		mockMvc.perform(put("/coffees/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(coffee)))
				.andExpect(status().isNotFound());

		verify(coffeeRepo, times(0)).save(any(Coffee.class));
	}

	@Test
	public void testDeleteCoffeeByIdNotFound() throws Exception {
		when(coffeeRepo.findById(1L)).thenReturn(java.util.Optional.empty());

		mockMvc.perform(delete("/coffees/1"))
				.andExpect(status().isNotFound());

		verify(coffeeRepo, times(0)).delete(any(Coffee.class));
	}

}