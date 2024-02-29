package com.ayshu.gct.spring.aop.concept.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springaop")
public class SpringAOPController {

	@GetMapping("/healthCheck")
	public ResponseEntity<String> getHealthCheckInfo(){
		return new ResponseEntity<String>("The Spring AOP Controller is running fine.", HttpStatus.OK);
	}
	
}
