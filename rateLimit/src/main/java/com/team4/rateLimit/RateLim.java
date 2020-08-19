package com.team4.rateLimit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Repeatable(RepAnno.class)
@Target(ElementType.METHOD)
public @interface RateLim {
	String user() default "anonymous";

	int rlimit() default 0;
}

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RepAnno {
	RateLim[] value();
}
