package com.example.demo.controller;

import com.example.demo.beans.ResponseBean;
import com.example.demo.beans.UserDetailsBean;

import com.example.demo.beans.UserLoginBean;
import com.example.demo.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = {"http://127.0.0.1:5500"})
@RestController
public class FormController {

	@Autowired
	UserDetailsServiceImpl userDetails;

//	@PostMapping(value = "/user-details")
//	public ModelAndView handleUserLogin(Model model, @RequestParam String firstName,
//										@RequestParam String lastName,
//										@RequestParam String emailId,
//										@RequestParam String dob) {
//
//		UserDetailsBean userDetailsBean = new UserDetailsBean();
//		userDetailsBean.setFirstName(firstName);
//		userDetailsBean.setLastName(lastName);
//		userDetailsBean.setEmailId(emailId);
//		userDetailsBean.setDob(dob);
//
//		ModelAndView modelAndView = new ModelAndView();
//
//		modelAndView.addObject("userDetailsBean", userDetailsBean);
//
//		modelAndView.setViewName("welcome");
//
//		return modelAndView;
//	}
//
//	@GetMapping(value = "/userDetails")
//	public String showUsers(Model model) {
//		if(!userDetails.showUsers().isEmpty()) {
//			model.addAttribute("users", userDetails.showUsers());
//			return "userDetails";
//		}
//		else {
//			model.addAttribute("msg", "No User Found!!!");
//			return "error";
//		}
//	}

	@PostMapping(value = "/createUser")
	public ResponseEntity<ResponseBean> handleUsers(@RequestBody UserDetailsBean userDetailsBean) {
		ResponseBean response = new ResponseBean();
		if(userDetails.addUser(userDetailsBean)){
			HttpHeaders headers = new HttpHeaders();
			headers.add("Unique-Id", userDetailsBean.getEmailId());
			response.setMessage("User is Added Successfully");
			response.setId(userDetailsBean.getEmailId());
			return new ResponseEntity<ResponseBean>(response, headers, HttpStatus.CREATED);
		}
		else {
			response.setMessage("User Already Exists.");
			response.setId(userDetailsBean.getEmailId());
			return new ResponseEntity<ResponseBean>(response, HttpStatus.ALREADY_REPORTED);
		}
	}

	@GetMapping("/getAllUser")
	public ResponseEntity showAllUser() {
		return new ResponseEntity<HashMap<String, UserDetailsBean>>(userDetails.showAllUsers(), HttpStatus.ACCEPTED);
	}


	@GetMapping("/getUser/{id}")
	public ResponseEntity showUser(@PathVariable("id") String Id) {
		ResponseBean response = new ResponseBean();
		if(userDetails.getUser(Id) != null) {
			return new ResponseEntity<UserDetailsBean>(userDetails.getUser(Id), HttpStatus.ACCEPTED);
		}
		else {
			response.setMessage("Id Not Found!!");
			response.setId(Id);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updateUser/{id}")
	public ResponseEntity updateUserDetails(@PathVariable("id") String Id, @RequestBody UserDetailsBean userDetailsBean) {
		ResponseBean response = new ResponseBean();
		if(userDetails.updateUser(Id,userDetailsBean)) {
			if(userDetails.checkAdmin(userDetails.getUserDetail().getEmailId(),userDetails.getUserDetail().getPassword())){
				HttpHeaders headers = new HttpHeaders();
				headers.add("Type", "admin");
				return new ResponseEntity<HashMap<String, UserDetailsBean>>(userDetails.showAllUsers(), HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<UserDetailsBean>(userDetails.getUser(Id), HttpStatus.ACCEPTED);
			}
		}
		else {
			response.setMessage("Id Not Found!!");
			response.setId(Id);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/deleteUser/{id}")
	public ResponseEntity deleteUsers(@PathVariable("id") String Id) {
		ResponseBean response = new ResponseBean();
		if(userDetails.deleteUser(Id)) {
			if(userDetails.checkAdmin(userDetails.getUserDetail().getEmailId(),userDetails.getUserDetail().getPassword())) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Type", "admin");
				return new ResponseEntity<HashMap<String, UserDetailsBean>>(userDetails.showAllUsers(), HttpStatus.ACCEPTED);
			} else{
				response.setMessage("User Details Deleted");
				response.setId(Id);
				return new ResponseEntity<ResponseBean>(response, HttpStatus.ACCEPTED);
			}
		}
		else {
			response.setMessage("Id Not Found!!");
			response.setId(Id);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity showDetails(@RequestBody UserLoginBean userLoginBean) {
		ResponseBean response = new ResponseBean();
		if(userDetails.checkAdmin(userLoginBean.getEmailId(),userLoginBean.getPassword())){
			HttpHeaders headers = new HttpHeaders();
			headers.add("Type", "admin");
			return new ResponseEntity<HashMap<String, UserDetailsBean>>(userDetails.showAllUsers(), HttpStatus.ACCEPTED);
		}
		else if(userDetails.checkUser(userLoginBean.getEmailId(),userLoginBean.getPassword())) {
			return new ResponseEntity<UserDetailsBean>(userDetails.getUser(userLoginBean.getEmailId()), HttpStatus.ACCEPTED);
		}
		else {
			response.setMessage("Wrong Credentials.");
			response.setId(userLoginBean.getEmailId());
			return new ResponseEntity<ResponseBean>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
