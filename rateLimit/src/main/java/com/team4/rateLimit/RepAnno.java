package com.team4.rateLimit;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepAnno {
    RateLim[] value();
}
