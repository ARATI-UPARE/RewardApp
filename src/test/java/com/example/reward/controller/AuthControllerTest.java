package com.example.reward.controller;

import com.example.reward.entity.User;
import com.example.reward.security.JwtUtil;
import com.example.reward.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class, excludeAutoConfiguration = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class })
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private JwtUtil jwtUtil;

	@Test
	void testRegisterUser() throws Exception {

		User user = new User();
		user.setId(1L);
		user.setUsername("john");
		user.setPassword("password");
		Mockito.when(userService.register(any(User.class))).thenReturn(user);

		String json = "{\"username\":\"john\",\"password\":\"password\"}";

		mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.username").value("john"));
	}

	@Test
	void testLoginUser() throws Exception {

		Mockito.when(userService.login("john", "password")).thenReturn("fake-jwt-token");

		String json = "{\"username\":\"john\",\"password\":\"password\"}";

		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.token").value("fake-jwt-token"));
	}
}