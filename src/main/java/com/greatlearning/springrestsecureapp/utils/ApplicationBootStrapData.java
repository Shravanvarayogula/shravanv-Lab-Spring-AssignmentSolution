package com.greatlearning.springrestsecureapp.utils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.greatlearning.springrestsecureapp.entity.Employee;
import com.greatlearning.springrestsecureapp.entity.Role;
import com.greatlearning.springrestsecureapp.entity.User;
import com.greatlearning.springrestsecureapp.repository.RoleRepository;
import com.greatlearning.springrestsecureapp.repository.UserRepository;
import com.greatlearning.springrestsecureapp.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class ApplicationBootStrapData {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
//	private final PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeService employeeService;

	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Application Ready Event is triggered");

		System.out.println("Creating Roles Admin and User");

		Role ADMIN = Role.builder().rolename("ADMIN").id(0).build();
		Role USER = Role.builder().rolename("USER").id(0).build();

		roleRepository.save(ADMIN);
		roleRepository.save(USER);

		System.out.println("Creating Employees");

		Employee emp1 = Employee.builder().firstName("a").lastName("b").email("ab@gmail.com").id(0).build();
		Employee emp2 = Employee.builder().firstName("b").lastName("c").email("bc@gmail.com").id(0).build();
		Employee emp3 = Employee.builder().firstName("c").lastName("d").email("cd@gmail.com").id(0).build();

		employeeService.save(emp1);
		employeeService.save(emp2);
		employeeService.save(emp3);

		System.out.println("Creating Users for Application with Admin and User Access");

		User sv = User.builder().firstname("s").lastname("v").email("sv@gmail.com").password("admin")
				.build();
		employeeService.saveUser(sv).addRole(ADMIN);

		userRepository.save(sv);
		User vl = User.builder().firstname("v").lastname("l").email("vl@gmail.com").password("user")
				.build();
		employeeService.saveUser(vl).addRole(USER);

		userRepository.save(vl);

		System.out.println("Created a Test User with below credentials");

		System.out.println(emp1.toString());
		System.out.println(emp2.toString());

	}
}
