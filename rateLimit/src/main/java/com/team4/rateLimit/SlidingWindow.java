package com.team4.rateLimit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;


public class SlidingWindow extends RateLimiterr {

  private final ConcurrentMap<Long, Integer> windows = new ConcurrentHashMap<Long, Integer>();

  public SlidingWindow(int defaultMaxRequestPerSec) {
    super(defaultMaxRequestPerSec); 
  }
  
  @Override
  public boolean allow(HttpServletRequest request) {
	System.out.println(request.getRemoteAddr());
	System.out.println(request.getRequestURL().toString());
	int maxRequestPerSec = defaultMaxRequestPerSec;
    long curTime = System.currentTimeMillis();
    long curWindowKey = curTime / 1000 * 1000;
    windows.putIfAbsent(curWindowKey, 0);
    long preWindowKey = curWindowKey - 1000;
    Integer preCount = windows.get(preWindowKey);
    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    StackTraceElement e = stacktrace[2];
    String methodName = e.getMethodName();
    System.out.println(methodName);
    if (preCount == null) {
      windows.put(curWindowKey, windows.get(curWindowKey)+1);
      return windows.get(curWindowKey) <= maxRequestPerSec;
    }

    double preWeight = 1 - (curTime - curWindowKey) / 1000.0;
    windows.put(curWindowKey, windows.get(curWindowKey)+1);
    long count = (long) (preCount * preWeight
        + windows.get(curWindowKey));
    return count <= maxRequestPerSec;
  }
}

