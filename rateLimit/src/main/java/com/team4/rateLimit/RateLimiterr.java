package com.team4.rateLimit;

import javax.servlet.http.HttpServletRequest;

public abstract class RateLimiterr {
	/**
	 * Variable to store default value.
	 */
	protected int defaultMaxRequestPerSec;
	
	/**
	 * Constructor that initializes the default rate limit value.
	 * 
	 * @param defaultMaxRequestPerSec
	 */
	public RateLimiterr(int defaultMaxRequestPerSec) {
		super();
		this.defaultMaxRequestPerSec = defaultMaxRequestPerSec;
	}
	
	public abstract boolean allow(HttpServletRequest request);
}
