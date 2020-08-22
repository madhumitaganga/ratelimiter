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
     * @param defaultMaxRequestPerSec default value for max requests per second.
     */
    public RateLimiterr(int defaultMaxRequestPerSec) {
        super();
        this.defaultMaxRequestPerSec = defaultMaxRequestPerSec;
    }

    /**
     * Method to check if request can be serviced or not.
     *
     * @param request Http request.
     * @return boolean true or false.
     */
    public abstract boolean allow(HttpServletRequest request);
}
