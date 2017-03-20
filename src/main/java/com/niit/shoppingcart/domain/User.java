package com.niit.shoppingcart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Component
public class User {
	
	@Id
	private String id;
	
	@NotEmpty(message = "Please Enter Your Name")
	private String name;
	
	//@Column(unique=true, nullable=false)
	//private String mail;
	
	@Min(5)
	@Max(10)
	private String password;
	private String contact;
	private String role;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	 * 
	 * 
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
