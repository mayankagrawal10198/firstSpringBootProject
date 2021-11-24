package com.example.demo.service;

import com.example.demo.beans.UserDetailsBean;

import java.util.HashMap;


interface UserDetailsService {

	HashMap<String,UserDetailsBean> showAllUsers();
	boolean addUser(UserDetailsBean user);
	UserDetailsBean getUser(String Id);
	boolean deleteUser(String Id);
	boolean updateUser(String Id, UserDetailsBean user);
	boolean checkAdmin(String em, String pass);
	boolean checkUser(String em, String pass);
}
