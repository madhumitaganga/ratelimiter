package com.team4.rateLimit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.servlet.http.HttpServletRequest;

import com.team4.config.JwtRequestFilter;
import com.team4.controllers.NameController;

public class SlidingWindow extends RateLimiterr {
    /**
     * Map to store the different UserAPI pairs.
     */
    private static final ConcurrentMap<String, ConcurrentMap<Long, Integer>> userAPIPairs = new ConcurrentHashMap<String, ConcurrentMap<Long, Integer>>();
    /**
     * Map to store the current userAPI pair window.
     */
    private static final ConcurrentMap<Long, Integer> windows = new ConcurrentHashMap<Long, Integer>();

    /**
     * Initializes the default rate limit value.
     *
     * @param defaultMaxRequestPerSec
     */
    public SlidingWindow(int defaultMaxRequestPerSec) {
        super(defaultMaxRequestPerSec);
    }

    /**
     * Receives the request and processes to determine if request can be served or
     * not.
     *
     * @param request Http request
     * @return boolean true if it can be serviced, boolean false if it cannot be
     * serviced.
     */
    @Override
    public boolean allow(HttpServletRequest request) {
        /**
         * Gets user from JwtRequestFilter class.
         */
        String user = JwtRequestFilter.User;
        System.out.println(user);

        /**
         * Extracts path from the URL given.
         */
        String path = "";
        try {
            URL url = new URL(request.getRequestURL().toString());
            path = url.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /**
         * Extracts the calling method.
         */
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getMethodName();

        /**
         * Obtains the object for the method.
         */
        Class<NameController> cls = NameController.class;
        Method m = null;
        Method[] methods = cls.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                m = method;
            }
        }

        /**
         * Retrieves all the annotations that exist for the method.
         * If any of them are from RepAnno, then it saves it to an array: lim.
         */
        Annotation[] annos = m.getAnnotations();
        RateLim[] lim = null;
        for (Annotation a : annos) {
            if (a instanceof RepAnno) {
                lim = ((RepAnno) a).value();
                break;
            }
        }

        /**
         * Finds rate limit for specific user, if user doesn't exist, then will take a default value.
         */
        int rlim = defaultMaxRequestPerSec;
        for (RateLim r : lim) {
            if (r.user().equals(user)) {
                rlim = r.rlimit();
            }
        }

        /**
         * Concatenates a string and stores/searches in the userAPIPairs hashmap.
         * Copies all the contents of the corresponding hashmap to windows hashmap.
         */
        String userAPI = user + "_" + path;
        if (userAPIPairs.containsKey(userAPI)) {
            windows.clear();
            windows.putAll(userAPIPairs.get(userAPI));
        } else {
            windows.clear();
            ConcurrentMap<Long, Integer> hashmap = new ConcurrentHashMap<Long, Integer>();
            userAPIPairs.put(userAPI, hashmap);
            windows.putAll(hashmap);
        }

        /**
         * Sliding window algorithm.
         */
        int maximumRequestPerSec = rlim;
        long currentTime = System.currentTimeMillis();
        long currentWindowKey = currentTime / 1000 * 1000;
        windows.putIfAbsent(currentWindowKey, 0);
        long previousWindowKey = currentWindowKey - 1000;
        Integer previousCount = windows.get(previousWindowKey);
        if (previousCount == null) {
            windows.put(currentWindowKey, windows.get(currentWindowKey) + 1);
            ConcurrentMap<Long, Integer> hashmap = userAPIPairs.get(userAPI);
            hashmap.putAll(windows);
            userAPIPairs.put(userAPI, hashmap);
            return windows.get(currentWindowKey) <= maximumRequestPerSec;
        }

        double previousWeight = 1 - (currentTime - currentWindowKey) / 1000.0;
        windows.put(currentWindowKey, windows.get(currentWindowKey) + 1);
        long count = (long) (previousCount * previousWeight + windows.get(currentWindowKey));
        ConcurrentMap<Long, Integer> hashmap = userAPIPairs.get(userAPI);
        hashmap.putAll(windows);
        userAPIPairs.put(userAPI, hashmap);
        return count <= maximumRequestPerSec;
    }

}
