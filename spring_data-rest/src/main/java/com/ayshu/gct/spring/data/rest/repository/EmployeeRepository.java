package com.ayshu.gct.spring.data.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ayshu.gct.spring.data.rest.repository.entity.Employee;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employee")
public interface EmployeeRepository extends JpaRepository<Employee, Long>  {
//extends JpaRepository<Employee, Long>{

	public List<Employee> findByJobId(Integer jobId);
}
