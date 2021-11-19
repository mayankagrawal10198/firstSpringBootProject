package com.example.demo.controller;

import com.example.demo.service.UserDetailsService;

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

	AtomicLong counter = new AtomicLong();

	List<UserDetailsService> users = new ArrayList<>();

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
//		UserDetailsService userDetailsService = new UserDetailsService();
//		userDetailsService.setFirstName(firstName);
//		userDetailsService.setLastName(lastName);
//		userDetailsService.setEmailId(emailId);
//		userDetailsService.setDob(dob);
//
//		ModelAndView modelAndView = new ModelAndView();
//
//		modelAndView.addObject("userDetailsService", userDetailsService);
//
//		modelAndView.setViewName("welcome");
//
//		return modelAndView;
//	}

	@GetMapping(value = "/user_details")
	public String showUsers(Model model) {
		model.addAttribute("users", users);
		return "user_details";
	}

	@PostMapping(value = "/user_details")
	public String handleUsers(Model model, @ModelAttribute UserDetailsService userDetailsService) {
		userDetailsService.setId(counter.incrementAndGet());
		users.add(userDetailsService);
		model.addAttribute("users",users);
		return "user_details";
	}

	@PostMapping(value = "/update_details")
	public String updateUserDetails(Model model, @ModelAttribute UserDetailsService userDetailsService) {
		Iterator itr = users.iterator();
		int i=0;
		while (itr.hasNext()) {
			UserDetailsService x = (UserDetailsService)itr.next();
			if (x.getId() == userDetailsService.getId()){
				users.set(i,userDetailsService);
				break;
			}
			i++;
		}
		return "redirect:/user_details";
	}

	@GetMapping(value = "/delete_user/{id}")
	public String deleteUsers(@PathVariable("id") long id) {
		Iterator itr = users.iterator();

		while (itr.hasNext()) {
			UserDetailsService x = (UserDetailsService)itr.next();
			if (x.getId() == id)
				itr.remove();
		}

		return "redirect:/user_details";
	}

	@GetMapping(value = "/update_user/{id}")
	public String updateUser(Model model, @PathVariable("id") long id) {
		Iterator itr = users.iterator();

		while (itr.hasNext()) {
			UserDetailsService x = (UserDetailsService)itr.next();
			if (x.getId() == id)
				model.addAttribute("user", x);
		}
		return "update_user";
	}

}
