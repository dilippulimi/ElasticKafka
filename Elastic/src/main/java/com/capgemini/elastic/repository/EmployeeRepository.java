package com.capgemini.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.elastic.entity.Employee;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String>{
	 
    List<Employee> findByFirstname(String firstName);
 
}