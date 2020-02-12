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
	
	static enum MONTH {
		JAN(1),FEB(2),MAR(3),APR(4),MAY(5),JUN(6),JUL(7),AUG(8),SEP(9),OCT(10),NOV(11),DEC(12);
		
		  MONTH(int number) {
		   }
		  
		  public static String getValue(int number) {
			  MONTH values[] = MONTH.values();
			  for(MONTH name : values) {
				  if(number == name.ordinal()) {
					 // System.out.println(name.name());
					  return name.name();
			  }
		  }
			  return  null;
	}
	}
	
	public static void main(String[] args) {
		MONTH.getValue(1);
		for(int i = 100 ; i < 207 ; i ++) {
			int dateVal = (int) (Math.random()*((31 - 1) + 1)) + 1;
			int monthNumber = (int) (Math.random()*((11 - 0) + 1)) + 0;
			int yearNumber1 = (int)(Math.random()*((9 - 8) + 1)) + 8;
			int yearNumber2 = (int) (Math.random()*9);
			System.out.print("UPDATE \"HR\".\"EMPLOYEES\" SET DOB = '");
			System.out.print(String.valueOf(dateVal).length() == 2 ? String.valueOf(dateVal) : "0" + String.valueOf(dateVal));
			System.out.print("/");
			System.out.print(MONTH.getValue(monthNumber));
			//System.out.print(String.valueOf(monthNumber).length() == 2 ? String.valueOf(monthNumber) : "0"+String.valueOf(monthNumber));
			System.out.print("/19" + String.valueOf(yearNumber1) + String.valueOf(yearNumber2)+"' ");
			System.out.println("WHERE EMPLOYEE_ID = "+i);
		}
		
	}
	
}
