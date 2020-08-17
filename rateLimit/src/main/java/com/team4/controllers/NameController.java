package com.team4.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.rateLimit.RateLimiterr;
import com.team4.rateLimit.SlidingWindow;

import lombok.RequiredArgsConstructor;
import models.Name;


@RestController
@RequiredArgsConstructor
public class NameController {

	private RateLimiterr rateLimiter = new SlidingWindow(1);
	
	@GetMapping("/name/getName")
	public ResponseEntity<Name> getName(HttpServletRequest request) {
		//System.out.println(request.getRemoteAddr());

		boolean canRun = rateLimiter.allow(request);
		if(canRun) {
			return new ResponseEntity<Name>(new Name("madhumita", "ganga"), HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
		}
	}
	
	@GetMapping("/name/getNameDiff")
	public ResponseEntity<Name> getNameDiff(HttpServletRequest request) {
		//System.out.println(request.getRemoteAddr());

		boolean canRun = rateLimiter.allow(request);
		if(canRun) {
			return new ResponseEntity<Name>(new Name("madhumita1", "ganga1"), HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
		}
	}
}
