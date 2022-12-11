package com.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.IProductDAO;
import com.entity.Product;
import com.entity.User;
import com.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductDAO productDAO;

	

	@GetMapping("/getUser")
	@ResponseBody
	public String getUserHandler() {
		User user = userService.getUser(5);
		System.out.println("Fecthed - " + user);
		return "index";
	}

	@GetMapping("/getAllUser")
	public String getAllUserHandler() {
		List<User> userList = userService.getAllUser();
		System.out.println(userList);
		return "index";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST) 
	@ResponseBody
	public ModelAndView addUserHandler(Model m, @ModelAttribute("user") User user, @RequestParam("fname") String fname,
			@RequestParam("mname") String mname, @RequestParam("lname") String lname) {
		ModelAndView modelAndView = new ModelAndView("register");
		if (this.userAlreadyPresent(user.getUserEmail())) {
			System.out.println("User already exists !!");
			String userMessage = "User already exists !! Try with different Email ";
			modelAndView.addObject("userMessage", userMessage);
			return modelAndView;
		}
		System.out.println(user);
		String fullName = "";
		if (mname.isBlank() && mname.isEmpty()) {
			fullName = fname + " " + lname;
		} else {
			fullName = fname + " " + mname + " " + lname;
		}
		user.setUserName(fullName);
		user.setUserEnabled(true);
		user.setUserRole("ROLE_USER");
		System.out.println(user);

		boolean addedUser = userService.addUser(user);

		System.out.println("Inside addUserHandler() - " + addedUser);

		ModelAndView modelAndViewLogin = new ModelAndView("login");
		String userMessage = "Registration successfull..You can Login now";
		modelAndViewLogin.addObject("userMessage", userMessage);
		return modelAndViewLogin;
	}

	private boolean userAlreadyPresent(String email) {
		if (!Objects.isNull(userService.getUser(email)))
			return true;

		return false;
	}

	@RequestMapping(value = "/loginInitiate", method = RequestMethod.POST)
	public String loginHandler(Model m, HttpServletRequest request, @RequestParam("userEmail") String userEmail,
			@RequestParam("userPassword") String userPassword) 
	{
		String page = "login";
		boolean loggedIn = false;
		
		if (this.userAlreadyPresent(userEmail)) 
		{
			User user = userService.getUser(userEmail);
			if (user.getUserPassword().equals(userPassword)) 
			{
				loggedIn = true;
				page =  "productDisplay"; // "loginSuccessUser";
				HttpSession session = request.getSession(); // VVI
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("userEmail", userEmail);
				//session.setMaxInactiveInterval(100); // after (..) seconds user gets log out automatically
				 Date date = new Date(session.getLastAccessedTime());
				System.out.println(date);
				//session.
				session.setAttribute("role", user.getUserRole());

				System.out.println("Logged in successfully");
			}

			else 
			{
				System.out.println("Bad credentials !! ");
				m.addAttribute("userMessage","Bad credentials !! ");
			}
		}
		else
		{
			m.addAttribute("userMessage","Invalid Email !!  Register First");
		}

		return page;
	}
	

	/*
	@RequestMapping(value = "/loginSuccessUser")
	public String loginSuccessUser() {
		return "loginSuccessUser";
	}
	*/
	
	@RequestMapping(value = "/logout")
	public String logoutHandler(HttpSession session, Model m) {
		session.invalidate();
		m.addAttribute("userMessage","Logged Out Successfully");
		return "login";
	}

	@GetMapping(value = "/updateUser")
	public String updateUserHandler() {
		User user = userService.getUser(9);
		user.setUserAddress(user.getUserAddress() + ", Near HDFC bank");
		userService.updateUser(user);
		return "index";
	}

	@GetMapping(value = "/deleteUser")
	public String deleteUserHandler() {
		return "not done yet";
	}
}
