package com.example.employeeproject.configurationdetails;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShowAPi {
	
	String value() default "";

}
