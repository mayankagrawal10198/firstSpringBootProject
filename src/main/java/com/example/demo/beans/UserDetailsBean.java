package com.example.demo.beans;

import java.util.Date;

public class UserDetailsBean {
	private String firstName;
	private String lastName;
	private String emailId;
	private String dob;
	private String password;
	private String date;

	public UserDetailsBean() {

	}

	public UserDetailsBean(String fn, String ln, String e, String d, String p) {
		this.firstName = fn;
		this.lastName = ln;
		this.emailId = e;
		this.dob = d;
		this.password = p;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return  "First Name = '" + firstName + '\'' +
				", Last Name = '" + lastName + '\'' +
				", Email Id = '" + emailId + '\'' +
				", DOB = '" + dob + '\'';
	}

}
