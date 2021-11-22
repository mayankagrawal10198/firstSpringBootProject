package com.example.demo.service;

import com.example.demo.beans.UserDetailsBean;

import java.util.HashMap;

interface UserDetailsService {

	public HashMap<String,UserDetailsBean> showUsers();
	public void addUser(UserDetailsBean user);
	public UserDetailsBean getUser(String Id);
	public void deleteUser(String Id);
	public void updateUser(UserDetailsBean user);

}
