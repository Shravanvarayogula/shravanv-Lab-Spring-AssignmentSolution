package com.greatlearning.springrestsecureapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.springrestsecureapp.service.UserDetailsSpringServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsSpringServiceImpl();
	}

	// One-way Encryption

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/springrestsecureapp/**", "/h2-console/").permitAll()
				.antMatchers("/springrestsecureapp/user", "/springrestsecureapp/role").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/springrestsecureapp/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/springrestsecureapp/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/springrestsecureapp/employees/*").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().httpBasic().and().cors().and().csrf().and().headers().frameOptions().disable();
	}

}
