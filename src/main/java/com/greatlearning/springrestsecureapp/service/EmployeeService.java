package com.greatlearning.springrestsecureapp.service;

import java.util.List;

import com.greatlearning.springrestsecureapp.entity.Employee;
import com.greatlearning.springrestsecureapp.entity.Role;
import com.greatlearning.springrestsecureapp.entity.User;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public List<User> listAllUsers();

	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public List<Employee> searchByFirstName(String firstName);
	
	public List<Employee> sortByFirstName(String order);
	
	public User saveUser(User user);
	
	public Role saveRole(Role role);

	public User getUserById(int id);
	
	public void deleteAllUsers();

	void deleteAllEmployees();

	

}


