package com.ayshu.gct.spring.aop.concept.aspect;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ayshu.gct.spring.aop.concept.security.HasEndpointAuthorities;

@Aspect
@Component
public class AuthorizationAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void restMethodExec() {

	}

	@Around(value = "restMethodExec() && @annotation(authorities)")
	public Object checkAround(ProceedingJoinPoint joinPoint, final HasEndpointAuthorities authorities)
			throws Throwable {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		if (!Objects.isNull(securityContext)) {
			final Authentication authentication = securityContext.getAuthentication();

			if (!Objects.isNull(authentication)) {
				final Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();

				if (Stream.of(authorities.securityAuthorities()).anyMatch(securityAuth ->

				userAuthorities.stream().anyMatch(userAuth -> userAuth.getAuthority()
						.equals(securityAuth.getValue())))) {
					if(log.isDebugEnabled()) {
						log.debug("User has the correct authorities required by endpoint {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());							
					}
						return joinPoint.proceed();
				} else {
					log.error("User does not have the correct authorities required by endpoint {} {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());							
					return new ResponseEntity<String>("User does not have the correct authorities required by endpoint", HttpStatus.FORBIDDEN);
				}
			} 
		}
		log.error("User is unauthorized to access the endpoint");
		return new ResponseEntity<String>("User is unauthorized to access the endpoint", HttpStatus.UNAUTHORIZED);
	}
}
