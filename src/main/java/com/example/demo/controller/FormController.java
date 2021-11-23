package com.example.demo.controller;

import com.example.demo.beans.UserDetailsBean;

import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class FormController {

	@Autowired
	UserDetailsServiceImpl userDetails;

	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/addUser";
	}

	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}

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

	@GetMapping(value = "/userDetails")
	public String showUsers(Model model) {
		if(!userDetails.showUsers().isEmpty()) {
			model.addAttribute("users", userDetails.showUsers());
			return "userDetails";
		}
		else {
			model.addAttribute("msg", "No User Found!!!");
			return "error";
		}
	}

	@PostMapping(value = "/userDetails")
	public String handleUsers(Model model, @ModelAttribute UserDetailsBean userDetailsBean) {
		if(userDetails.addUser(userDetailsBean)){
			model.addAttribute("users",userDetails.showUsers());
			return "userDetails";
		}
		else {
			model.addAttribute("msg", "User Already Exists.");
			return "error";
		}
	}

	@PostMapping(value = "/updateUser/{id}")
	public String updateUserDetails(Model model, @PathVariable("id") String Id, @ModelAttribute UserDetailsBean userDetailsBean) {
		if(userDetails.updateUser(Id,userDetailsBean)) {
			return "redirect:/userDetails";
		}
		else {
			model.addAttribute("msg", "User Already Exists.");
			return "error";
		}
	}

	@GetMapping(value = "/deleteUser/{id}")
	public String deleteUsers(Model model, @PathVariable("id") String Id) {
		if(userDetails.deleteUser(Id)) {
			return "redirect:/userDetails";
		}
		else {
			model.addAttribute("msg", "User Does not Exists.");
			return "error";
		}
	}

	@GetMapping(value = "/updateUser/{id}")
	public String updateUser(Model model, @PathVariable("id") String Id) {
		if(userDetails.getUser(Id) != null){
			model.addAttribute("user", userDetails.getUser(Id));
			return "updateUser";
		}
		else {
			model.addAttribute("msg", "User Does not Exists.");
			return "error";
		}
	}

}
