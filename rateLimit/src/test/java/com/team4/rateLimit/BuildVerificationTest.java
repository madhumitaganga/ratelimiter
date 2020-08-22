package com.team4.rateLimit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.team4.controllers.NameController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BuildVerificationTest {

    @Autowired
    private NameController controller;

    /**
     * Basic test to verify if controller is not null.
     *
     * @throws Exception
     */
    @Test
    public void smokeTest() throws Exception {
        assertThat(controller).isNotNull();
    }
}
