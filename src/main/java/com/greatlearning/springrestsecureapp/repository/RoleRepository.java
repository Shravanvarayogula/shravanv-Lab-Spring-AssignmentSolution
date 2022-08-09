package com.greatlearning.springrestsecureapp.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.springrestsecureapp.entity.Role;
import com.greatlearning.springrestsecureapp.entity.User;

@Repository

public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findBy(User user, PageRequest firstPageWithTwoElements);
	
	

}
