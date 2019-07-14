package com.sample.model;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dob;
	private String emailId;
	private Address address;
	private String imageUrl;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Employee() {
		super();
	}
	public Employee(long id, String firstName, String middleName, String lastName, String dob, String emailId,String imageUrl,Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dob = dob;
		this.emailId = emailId;
		this.imageUrl = imageUrl;
		this.address = address;
		
	}
	
	
	

}
