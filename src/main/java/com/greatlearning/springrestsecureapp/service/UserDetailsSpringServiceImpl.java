package com.greatlearning.springrestsecureapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.springrestsecureapp.config.AppUserDetails;
import com.greatlearning.springrestsecureapp.entity.User;
import com.greatlearning.springrestsecureapp.repository.UserRepository;

public class UserDetailsSpringServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new AppUserDetails(user);
	}
}
