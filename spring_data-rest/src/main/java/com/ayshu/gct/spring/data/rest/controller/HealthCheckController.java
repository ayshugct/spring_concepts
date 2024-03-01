package com.ayshu.gct.spring.data.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

	@GetMapping("/healthCheck")
	public ResponseEntity<String> getHealthCheck() {
		return ResponseEntity.ok().body("The Spring Data Rest is running fine");
	}
}
