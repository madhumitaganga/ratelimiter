package com.team4.rateLimit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.team4.controllers.NameController;


public class WebApplicationTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	NameController nameController;
	
	@Test
	public void unknownRoute() throws Exception {
		this.mockMvc.perform(get("/falsePath")).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testGetName() throws Exception {
		this.mockMvc.perform(get("/name/getName")).andExpect(status().isOk()); 
	}
	
	@Test
	public void testGetNameDiff() throws Exception {
		this.mockMvc.perform(get("/name/getNameDiff")).andExpect(status().isOk()); 
	}
}
