package com.example.demo.controller;

import com.example.demo.beans.UserDetailsBean;

import com.example.demo.beans.UserLoginBean;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
public class FormController {

	@Autowired
	UserDetailsServiceImpl userDetails;

	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/signUp";
	}

	@RequestMapping("/signUp")
	public String addUser() {
		return "signUp";
	}

	@RequestMapping("/login")
	public String loginUser() {
		return "login";
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

	@PostMapping(value = "/newUser")
	public String handleUsers(Model model, @ModelAttribute UserDetailsBean userDetailsBean) {
		if(userDetails.addUser(userDetailsBean)){
			model.addAttribute("user",userDetails.getUser(userDetailsBean.getEmailId()));
			return "particularUser";
		}
		else {
			model.addAttribute("msg", "User Already Exists.");
			return "error";
		}
	}

	@PostMapping("/particularUser")
	public String showDetails(Model model, @ModelAttribute UserLoginBean userLoginBean) {
		if(userDetails.checkAdmin(userLoginBean.getEmailId(),userLoginBean.getPassword())){
			model.addAttribute("users",userDetails.showAllUsers());
			return "userDetails";
		}
		else if(userDetails.checkUser(userLoginBean.getEmailId(),userLoginBean.getPassword())) {
			model.addAttribute("user", userDetails.getUser(userLoginBean.getEmailId()));
			return "particularUser";
		}
		else {
			model.addAttribute("msg", "Wrong Credentials!!!");
			return "error";
		}
	}

	@GetMapping("/particularUser/{id}")
	public String showUser(Model model, @PathVariable("id") String Id) {
		model.addAttribute("user", userDetails.getUser(Id));
		return "particularUser";
	}

	@PostMapping(value = "/updateUser/{id}")
	public String updateUserDetails(Model model, @PathVariable("id") String Id, @ModelAttribute UserDetailsBean userDetailsBean) {
		if(userDetails.updateUser(Id,userDetailsBean)) {
			if(userDetails.checkAdmin(userDetails.getUserDetail().getEmailId(),userDetails.getUserDetail().getPassword())){
				model.addAttribute("users",userDetails.showAllUsers());
				return "userDetails";
			}else {
				return "redirect:/particularUser/" + Id;
			}
		}
		else {
			model.addAttribute("msg", "User Already Exists.");
			return "error";
		}
	}

	@GetMapping(value = "/deleteUser/{id}")
	public String deleteUsers(Model model, @PathVariable("id") String Id) {
		if(userDetails.deleteUser(Id)) {
			if(userDetails.checkAdmin(userDetails.getUserDetail().getEmailId(),userDetails.getUserDetail().getPassword())) {
				model.addAttribute("users",userDetails.showAllUsers());
				return "userDetails";
			} else{
				return "redirect:/login";
			}
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
