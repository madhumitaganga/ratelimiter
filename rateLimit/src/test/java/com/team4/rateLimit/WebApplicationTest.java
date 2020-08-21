package com.team4.rateLimit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.team4.controllers.JwtAuthenticationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@WebMvcTest(JwtAuthenticationController.class)
public class WebApplicationTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void unknownRoute() throws Exception {
		this.mockMvc.perform(get("/falsePath")).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testGetName() throws Exception {
		this.mockMvc.perform(get("/name/getName").header("Authorization" , "FAKETOKEN")).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testGetNameDiff() throws Exception {
		this.mockMvc.perform(get("/name/getNameDiff").header("Authorization" , "FAKETOKEN")).andExpect(status().is4xxClientError());
	}
	
	 protected RequestPostProcessor testUser() {
	        return user("user1").password("password");
	    }
	 
	@Test
	public void testAuthenticate() throws Exception {
		this.mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).with(csrf())
		           .content("{ \"username\": \"user1\", \"password\": \"password\" }") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk());
	}
}
