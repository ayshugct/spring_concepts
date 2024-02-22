package com.ayshu.gct.spring.aop.concept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayshu.gct.spring.aop.concept.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}