package com.example.demo.service;


import com.example.demo.beans.UserDetailsBean;

import java.util.HashMap;

interface UserDetailsService {

	public HashMap<Long,UserDetailsBean> showUsers();
	public void addUser(UserDetailsBean user);
	public UserDetailsBean getUser(Long Id);
	public void deleteUser(Long Id);
	public void updateUser(UserDetailsBean user);

}
