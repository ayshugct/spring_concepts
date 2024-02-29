package com.ayshu.gct.spring.aop.concept.security;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ayshu.gct.spring.aop.concept.constant.SecurityAuthorities;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
   
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
 		
 		http.cors();
 		http.csrf().disable();
 		
 		http.authorizeHttpRequests(auth -> auth
// 				.requestMatchers(new AntPathRequestMatcher("/employee/**"))
// 				.hasAnyRole(SecurityAuthorities.USER.getValue())
 				.requestMatchers(new AntPathRequestMatcher("**"))
 				.permitAll()
 				.anyRequest()
 				.authenticated());
 		
 		http.oauth2ResourceServer(config -> getOAuth2Config(config));
 		http.oauth2Login(Customizer.withDefaults()).logout();
 		return http.build();
    }
	
	public OAuth2ResourceServerConfigurer<HttpSecurity> getOAuth2Config(OAuth2ResourceServerConfigurer<HttpSecurity> oauthConfig){
		
		return oauthConfig.jwt(jwtConfig -> jwtConfig.jwtAuthenticationConverter(jwt -> getKeyClockAuthenticationConvertor(jwt)));
	}
	
	public JwtAuthenticationToken getKeyClockAuthenticationConvertor(Jwt jwt) {
		
		Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");	
		
		Collection<String> realmRoles = realmAccess.get("roles");
		
		List<SimpleGrantedAuthority> authList = realmRoles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return new JwtAuthenticationToken(jwt, authList);
	}
}