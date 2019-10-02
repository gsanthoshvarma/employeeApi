package com.sample.model;

public class Address {
	
	private int id;
	private String address;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public Address() {
		super();
	}
	
	public Address(String address,String city,String state,String country,String pincode) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	
	public static void main(String[] args) {
		System.out.println((int) (Math.random()*31)+"/"+(int) (Math.random()*13)+"/19"+ ((int)(Math.random()*((9 - 8) + 1)) + 8));
	}
	
}
