package com.team4.rateLimit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class GetNameFakeToken {

    @Autowired
    MockMvc mockMvc;

    /**
     * Method to test if /name/getName will return error if token is wrong.
     *
     * @throws Exception
     */
    @Test
    public void testGetNameDiff() throws Exception {
        this.mockMvc.perform(get("/name/getName").header("Authorization" , "FAKETOKEN")).andExpect(status().is4xxClientError());
    }
}


