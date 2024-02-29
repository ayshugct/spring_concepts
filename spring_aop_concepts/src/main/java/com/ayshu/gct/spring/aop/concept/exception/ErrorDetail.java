package com.ayshu.gct.spring.aop.concept.exception;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {

	private Date timestamp;
	private String message;
	private String details;
}
