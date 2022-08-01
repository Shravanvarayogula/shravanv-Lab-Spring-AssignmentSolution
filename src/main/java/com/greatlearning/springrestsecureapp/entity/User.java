package com.greatlearning.springrestsecureapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	// email will be used as username to uniquely identifying users
	@Column(name = "username")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> rolename ;

	public void addRole(Role role) {
		if (this.rolename == null) {
			this.rolename = new ArrayList<>();
		}
		this.rolename.add(role);
		role.setToUser(this);
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public List<Role> getRolename() {
		// TODO Auto-generated method stub
		return this.rolename;
	}

	public void setPassword(String encode) {
		this.password = encode;

	}
}
