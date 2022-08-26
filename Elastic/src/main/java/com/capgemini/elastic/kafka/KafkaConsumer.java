package com.capgemini.elastic.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.capgemini.elastic.entity.Employee;
import com.capgemini.elastic.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumer {
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "elastic_create_employee")
    public void processMessageCreateEmployee(String content){
        System.out.println("Message received: " + content);
        
        try {
        	Employee employee = objectMapper.readValue(content,  Employee.class);
        	employeeService.saveEmployee(employee);
		} catch (JsonProcessingException e) {
			System.out.println("Employee is not formatted properly");
		}
    }

}
