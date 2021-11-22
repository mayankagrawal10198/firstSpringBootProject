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

		model.addAttribute("users", userDetails.showUsers());
		return "userDetails";
	}

	@PostMapping(value = "/userDetails")
	public String handleUsers(Model model, @ModelAttribute UserDetailsBean userDetailsBean) {
		userDetails.addUser(userDetailsBean);
		model.addAttribute("users",userDetails.showUsers());
		return "userDetails";
	}

	@PostMapping(value = "/updateDetails")
	public String updateUserDetails(Model model, @ModelAttribute UserDetailsBean userDetailsBean) {
		userDetails.updateUser(userDetailsBean);
		return "redirect:/userDetails";
	}

	@GetMapping(value = "/deleteUser/{id}")
	public String deleteUsers(@PathVariable("id") String Id) {
		userDetails.deleteUser(Id);
		return "redirect:/userDetails";
	}

	@GetMapping(value = "/updateUser/{id}")
	public String updateUser(Model model, @PathVariable("id") String Id) {
		model.addAttribute("user", userDetails.getUser(Id));
		return "updateUser";
	}

}
