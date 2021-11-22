package com.example.demo.controller;

import com.example.demo.beans.UserDetailsBean;

import com.example.demo.service.UserDetailsServiceImpl;
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

	UserDetailsServiceImpl userDetails = new UserDetailsServiceImpl();

	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/add_user";
	}

	@GetMapping("/add_user")
	public String addUser() {
		return "add_user";
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

	@GetMapping(value = "/user_details")
	public String showUsers(Model model) {
		model.addAttribute("users", userDetails.showUsers());
		return "user_details";
	}

	@PostMapping(value = "/user_details")
	public String handleUsers(Model model, @ModelAttribute UserDetailsBean userDetailsBean) {
		userDetails.addUser(userDetailsBean);
		model.addAttribute("users",userDetails.showUsers());
		return "user_details";
	}

	@PostMapping(value = "/update_details")
	public String updateUserDetails(Model model, @ModelAttribute UserDetailsBean userDetailsBean) {
		userDetails.updateUser(userDetailsBean);
		return "redirect:/user_details";
	}

	@GetMapping(value = "/delete_user/{id}")
	public String deleteUsers(@PathVariable("id") long Id) {
		userDetails.deleteUser(Id);
		return "redirect:/user_details";
	}

	@GetMapping(value = "/update_user/{id}")
	public String updateUser(Model model, @PathVariable("id") Long Id) {
		model.addAttribute("user", userDetails.getUser(Id));
		return "update_user";
	}

}
