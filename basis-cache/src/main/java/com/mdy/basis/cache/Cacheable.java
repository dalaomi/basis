package com.mdy.basis.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented 
public @interface Cacheable {
	String region();
	String key();
}
