package com.ayshu.gct.spring.aop.concept.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception e, WebRequest req){
		ErrorDetail errDetail = new ErrorDetail(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
