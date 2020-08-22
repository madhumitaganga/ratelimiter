package com.team4.rateLimit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

@Documented
@Repeatable(RepAnno.class)
@Target(ElementType.METHOD)
public @interface RateLim {
	String user() default "anonymous";

	int rlimit() default 0;
}
