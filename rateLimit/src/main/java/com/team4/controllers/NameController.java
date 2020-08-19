package com.team4.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team4.rateLimit.RateLim;
import com.team4.rateLimit.RateLimiterr;
import com.team4.rateLimit.SlidingWindow;

import lombok.RequiredArgsConstructor;
import models.Name;

@RestController
@RequiredArgsConstructor
public class NameController {

	private RateLimiterr rateLimiter = new SlidingWindow(1);

	/**
	 * Receives a request and passes it through a rate limiter. METHOD = Get.
	 * 
	 * @param HttpServletRequest.
	 * @return Object of class Name with Http Status.
	 */
	@RateLim(user = "user1", rlimit = 1)
	@RateLim(user = "user2", rlimit = 0)
	@GetMapping("/name/getName")
	public ResponseEntity<Name> getName(HttpServletRequest request) {
		boolean canRun = rateLimiter.allow(request);
		if (canRun) {
			return new ResponseEntity<Name>(new Name("madhumita", "ganga"), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
		}
	}

	/**
	 * Receives a request and passes it through a rate limiter. METHOD = Get.
	 * 
	 * @param HttpServletRequest.
	 * @return Object of class Name with Http Status.
	 */
	@RateLim(user = "user1", rlimit = 1)
	@RateLim(user = "user2", rlimit = 1)
	@GetMapping("/name/getNameDiff")
	public ResponseEntity<Name> getNameDiff(HttpServletRequest request) {
		boolean canRun = rateLimiter.allow(request);
		if (canRun) {
			return new ResponseEntity<Name>(new Name("mansi", "singh"), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
		}
	}
}
