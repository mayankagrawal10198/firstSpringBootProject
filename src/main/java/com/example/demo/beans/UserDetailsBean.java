package com.example.demo.beans;

import java.util.Date;

public class UserDetailsBean {
	private String name;
	private String emailId;
	private String dob;
	private String password;
	private String date;

	public UserDetailsBean() {

	}

	public UserDetailsBean(String fn, String e, String d, String p) {
		this.name = fn;
		this.emailId = e;
		this.dob = d;
		this.password = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return  "Name = '" + name + '\'' +
				", Email Id = '" + emailId + '\'' +
				", DOB = '" + dob + '\'';
	}

}
