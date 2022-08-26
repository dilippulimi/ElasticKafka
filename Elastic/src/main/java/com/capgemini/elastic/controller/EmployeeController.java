package com.capgemini.elastic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.elastic.entity.Employee;
import com.capgemini.elastic.kafka.KafkaSender;
import com.capgemini.elastic.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/elastic")
public class EmployeeController {
	
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	KafkaSender kafkaSender;

		@PostMapping(path= "/createEmployee")
	    public String createEmployee(@RequestBody Employee employee) {
			 if(employeeService.employeeExists(employee)) {
		    	   return "Employee is exists with same id"; 
		       }
			 else {		
							try {
								kafkaSender.sendCreateEmployee(objectMapper.writeValueAsString(employee));
								return "Employee data is sent for creation";
							} catch (JsonProcessingException e) {
								return "Employee is not formatted properly";
							}
			 }
			
	    }
	 
	    @GetMapping("/findEverything")
	    public Iterable<Employee> findAllEmployees() {
	        return employeeService.findAll();
	    }
	 
	    @GetMapping("/findByFName/{firstName}")
	    public List<Employee> findByFirstName(@PathVariable String firstName) {
	        return employeeService.findByFirstname(firstName);
	    }
	    
	    @PutMapping("/updateEmployee")
	    public String updateEmployee(@RequestBody Employee employee) {
	        return employeeService.updateEmployee(employee);
	    }
	    
	    @DeleteMapping("/deleteEmployeeById/{id}")
	    public String deleteEmployeeById(@PathVariable String id) {
	        return employeeService.deleteEmployeeById(id);
	    }
	    
	    
	    
}
