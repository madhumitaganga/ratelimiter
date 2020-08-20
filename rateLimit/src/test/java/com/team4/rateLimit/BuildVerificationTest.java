package com.team4.rateLimit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team4.controllers.NameController;

@SpringBootTest
public class BuildVerificationTest {

	@Autowired
	private NameController controller;

	@Test
	public void smokeTest() throws Exception {
		assertThat(controller).isNotNull();
	}
}
