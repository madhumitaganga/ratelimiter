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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@WebMvcTest(JwtAuthenticationController.class)
public class AuthenticateTest {
    @Autowired
    MockMvc mockMvc;

    /**
     * Method to check if /authenticate returns 200 OK http status.
     *
     * @throws Exception
     */
    @Test
    public void testAuthenticate() throws Exception {
        this.mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user1\", \"password\": \"password\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

