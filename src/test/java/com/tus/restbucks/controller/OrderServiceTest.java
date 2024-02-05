package com.tus.restbucks.controller;

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
import com.tus.restbucks.dao.OrderRepository;
import com.tus.restbucks.dto.Order;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderRepository orderRepo;

	@Test
	public void testCreateOrder() throws Exception {
		Order order = new Order();
		// set properties on the order
		when(orderRepo.save(any(Order.class))).thenReturn(order);

		mockMvc.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isOk());

		verify(orderRepo, times(1)).save(any(Order.class));
	}

	@Test
	public void testGetAllOrders() throws Exception {
		mockMvc.perform(get("/orders"))
				.andExpect(status().isOk());

		verify(orderRepo, times(1)).findAll();
	}

	@Test
	public void testGetOrderById() throws Exception {
		Order order = new Order();
		// set properties on the order
		when(orderRepo.findById(1L)).thenReturn(java.util.Optional.of(order));

		mockMvc.perform(get("/orders/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testUpdateOrder() throws Exception {
		Order order = new Order();
		// set properties on the order
		when(orderRepo.findById(1L)).thenReturn(java.util.Optional.of(order));

		mockMvc.perform(put("/orders/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isOk());

		verify(orderRepo, times(1)).findById(1L);
		verify(orderRepo, times(1)).save(any(Order.class));
	}

	@Test
	public void testDeleteOrderById() throws Exception {
		Order order = new Order();
		// set properties on the order
		when(orderRepo.findById(1L)).thenReturn(java.util.Optional.of(order));

		mockMvc.perform(delete("/orders/1"))
				.andExpect(status().isOk());

		verify(orderRepo, times(1)).findById(1L);
		verify(orderRepo, times(1)).delete(any(Order.class));
	}

	@Test
	public void testGetOrderByIdNotFound() throws Exception {
		when(orderRepo.findById(1L)).thenReturn(java.util.Optional.empty());

		mockMvc.perform(get("/orders/1"))
				.andExpect(status().isNotFound());
	}

}
