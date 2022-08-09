package com.greatlearning.springrestsecureapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.greatlearning.springrestsecureapp.entity.Employee;
import com.greatlearning.springrestsecureapp.entity.Role;
import com.greatlearning.springrestsecureapp.entity.User;
import com.greatlearning.springrestsecureapp.repository.RoleRepository;
import com.greatlearning.springrestsecureapp.repository.UserRepository;
import com.greatlearning.springrestsecureapp.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ApplicationBootStrapData {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeService employeeService;

	@EventListener(ApplicationReadyEvent.class)

	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Application Ready Event is triggered");
		System.out.println("====================================================");
		System.out.println("Creating Employees");
		System.out.println("====================================================");

		Employee emp1 = Employee.builder().firstName("c").lastName("d").email("cd@gmail.com").id(0).build();
		employeeService.save(emp1);

		System.out.println("====================================================");
		System.out.println("Creating Roles Admin and User");
		System.out.println("====================================================");
		Role ADMIN = Role.builder().id(1).rolename("ADMIN").build();
		Role USER = Role.builder().id(2).rolename("USER").build();
		roleRepository.save(ADMIN);
		roleRepository.save(USER);

		System.out.println("====================================================");
		System.out.println("Creating Users for Application with Admin and User Access");
		System.out.println("====================================================");

		List<Role> adminRoles = new ArrayList<Role>();
		// adminRoles.add(USER);
		adminRoles.add(ADMIN);

		List<Role> generalRole = new ArrayList<Role>();
		generalRole.add(USER);

		System.out.println("Admin Roles: -> " + adminRoles.size());
		System.out.println("General Roles: -> " + generalRole.size());

		User sv = User.builder().firstname("s").lastname("v").email("sv@gmail.com").password("admin")
				.rolename(adminRoles).build();
		System.out.println("sv roles: " + sv.getRolename());

		employeeService.saveUser(sv);

		System.out.println("sv: " + sv);
		userRepository.save(sv);

		User vl = User.builder().firstname("v").lastname("l").email("vl@gmail.com").password("user")
				.rolename(generalRole).build();
		System.out.println("vl roles: " + vl.getRolename());
		 //vl.addRole(USER);
		employeeService.saveUser(vl);
		userRepository.save(vl);

		System.out.println("Created a Test employees with below credentials");

		System.out.println(emp1.toString());

		System.out.println("Created a Test User with below credentials");
		System.out.println("User with Admin Access : " + "Username: " + sv.getEmail() + "Password: " + sv.getPassword()
				+ "Roles: " + sv.getRolename());
		System.out.println("User with basic Access : " + "Username: " + vl.getEmail() + "Password: " + vl.getPassword()
				+ "Roles: " + vl.getRolename());

	}
}
