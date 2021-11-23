package com.example.demo.service;

import com.example.demo.beans.UserDetailsBean;

import java.util.HashMap;
import java.util.Optional;

interface UserDetailsService {

	HashMap<String,UserDetailsBean> showUsers();
	boolean addUser(UserDetailsBean user);
	UserDetailsBean getUser(String Id);
	boolean deleteUser(String Id);
	boolean updateUser(String Id, UserDetailsBean user);

}
