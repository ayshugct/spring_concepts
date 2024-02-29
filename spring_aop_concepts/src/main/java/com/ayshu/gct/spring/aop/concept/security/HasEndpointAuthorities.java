package com.ayshu.gct.spring.aop.concept.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ayshu.gct.spring.aop.concept.constant.SecurityAuthorities;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface HasEndpointAuthorities {

	SecurityAuthorities[] securityAuthorities();
}
