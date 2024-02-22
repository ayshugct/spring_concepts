package com.ayshu.gct.spring.aop.concept.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ayshu.gct.spring.aop.concept.model.Employee;
import com.ayshu.gct.spring.aop.concept.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository empRepository) {
		this.employeeRepository = empRepository;
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long employeeId) throws Exception {
		return employeeRepository.findById(employeeId);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws Exception {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));
		employee.setId(employeeDetails.getId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public Employee deleteEmployee(Long employeeId) throws Exception {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		return employee;
	}
}