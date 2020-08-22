package com.team4.rateLimit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class AuthenticateTestNoSecurity {
    @Autowired
    MockMvc mockMvc;

    /**
     * Method to check that /authenticate will give 403 error with no proper security.
     *
     * @throws Exception
     */
    @Test
    public void testAuthenticateForbidden() throws Exception {
        this.mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user1\", \"password\": \"password\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}

