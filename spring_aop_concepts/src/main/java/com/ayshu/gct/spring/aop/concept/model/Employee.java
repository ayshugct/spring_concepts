package com.ayshu.gct.spring.aop.concept.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	
	@Id
	@Column(name="employee_id")
	private Long id;
	
	private String firstName;
	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	private Date hireDate;
	private Integer jobId;
	private BigDecimal salary;
	private Integer managerId;
	private Integer departmentId;
	
}
