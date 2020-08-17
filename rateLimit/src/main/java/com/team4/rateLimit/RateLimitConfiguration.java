package com.team4.rateLimit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.util.concurrent.RateLimiter;

@Configuration
public class RateLimitConfiguration {
	@Bean
	public RateLimiter rateLimiter() {
		return RateLimiter.create(0.1d);
	}
}
