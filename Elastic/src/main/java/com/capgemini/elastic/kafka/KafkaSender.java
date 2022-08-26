package com.capgemini.elastic.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
		
	public void sendCreateEmployee(String message) {
		
		String kafkaTopic = "elastic_create_employee";
	    kafkaTemplate.send(kafkaTopic, message);
	  
	}

}
