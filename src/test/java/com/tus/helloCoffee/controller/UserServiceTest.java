package com.tus.helloCoffee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tus.helloCoffee.dao.UserRepository;
import com.tus.helloCoffee.dto.User;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepo;

	@Test
	public void testCreateUser() throws Exception {
		User user = new User("testUser", "password", "customer");
		// set properties on the user
		when(userRepo.save(any(User.class))).thenReturn(user);

		mockMvc.perform(post("/users/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(status().isOk());

		verify(userRepo, times(1)).save(any(User.class));
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk());

		verify(userRepo, times(1)).findAll();
	}

	@Test
	public void testGetUserByUsername() throws Exception {
		User user = new User();
		// set properties on the user
		when(userRepo.findByUsername("username")).thenReturn(user);

		mockMvc.perform(get("/users/username"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testUpdateUser() throws Exception {
		User user = new User();
		// set properties on the user
		when(userRepo.findByUsername("username")).thenReturn(user);

		mockMvc.perform(put("/users/username")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(status().isOk());

		verify(userRepo, times(1)).save(any(User.class));
	}

}