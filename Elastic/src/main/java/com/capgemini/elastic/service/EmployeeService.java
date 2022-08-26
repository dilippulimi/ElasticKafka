package com.capgemini.elastic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import com.capgemini.elastic.entity.Employee;
import com.capgemini.elastic.repository.EmployeeRepository;
import com.capgemini.elastic.service.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {
		
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchTemplate;
		
	public Employee saveEmployee(Employee employee) {
		return elasticsearchTemplate.save(employee);
	}

	public Iterable<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public boolean employeeExists(Employee employee) {
		return employeeRepository.existsById(employee.getId());
	}

	public List<Employee> findByFirstname(String firstName) {
		return employeeRepository.findByFirstname(firstName);
	}

	public String updateEmployee(Employee employee)  {
		
		if(employeeRepository.existsById(employee.getId())) {
			 employeeRepository.save(employee);
			 return "Employee updated";
		}
		throw new EmployeeNotFoundException("Employee not Found");	
	}

	public String deleteEmployeeById(String id) {
		if(employeeRepository.existsById(id)) {
		 employeeRepository.deleteById(id);
		 return "Employee Deleted";
		}
		throw new EmployeeNotFoundException("Employee not Found");
	}
	

}
