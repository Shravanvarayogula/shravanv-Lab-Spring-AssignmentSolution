package com.greatlearning.springrestsecureapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.springrestsecureapp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);
	
	List<Employee> findAllByOrderByFirstNameAsc();
	
	List<Employee> findAllByOrderByFirstNameDesc();

	
	
}
