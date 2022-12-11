package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String indexPageHandler()
	{
		return "login";
	}
	@RequestMapping(value="/register")
	public String registerHandler()
	{
		return "register";
	}
	@RequestMapping(value="/login")
	public String loginHandler()
	{
		return "login";
	}
	
	@RequestMapping(value="/homepageUponLogin", method = RequestMethod.POST)
	public String homepageUponLoginHandler()
	{
		return "index";
	}
	
	@RequestMapping(value="/payment", method=RequestMethod.POST)
	public String paymentHandler()
	{
		return "payment";
	}
	
	
}
