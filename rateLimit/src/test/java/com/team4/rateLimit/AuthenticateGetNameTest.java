package com.team4.rateLimit;

import com.team4.controllers.JwtAuthenticationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@WebMvcTest(JwtAuthenticationController.class)
public class AuthenticateGetNameTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * Method to check if full functioning is happening.
     * Gets response object from /authenticate and sends it to name/getName/Diff
     * Will throw error TOO_MANY_REQUESTS if limit exceeds.
     *
     * @throws Exception
     */
    @Test
    public void testAuthenticateGetName() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user2\", \"password\": \"password\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String token = result.getResponse().getContentAsString();
        String tokenValue = token.substring(10, token.length() - 2);
        System.out.println(tokenValue);
        this.mockMvc.perform(get("/name/getNameDiff").header("Authorization", tokenValue)).andExpect(status().isOk());

    }
}


