package com.greatlearning.springrestsecureapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.springrestsecureapp.entity.Role;
import com.greatlearning.springrestsecureapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(String email);

	public List<User> findBy(Role role, PageRequest firstPageWithTwoElements);

}
