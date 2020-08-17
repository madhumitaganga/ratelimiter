package com.team4.rateLimit;

import javax.servlet.http.HttpServletRequest;

public abstract class RateLimiterr {
	protected int defaultMaxRequestPerSec;

	public RateLimiterr(int defaultMaxRequestPerSec) {
		super();
		this.defaultMaxRequestPerSec = defaultMaxRequestPerSec;
	}
	
	public abstract boolean allow(HttpServletRequest request);
}
