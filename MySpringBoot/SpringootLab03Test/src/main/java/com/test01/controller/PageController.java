package com.test01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String home() { return "home";}
	
	@GetMapping("/admin")
	public String admin() { return "admin";}
	
	@GetMapping("/user")
	public String user() { return "user";}
	
	@GetMapping("/login")
	public String login() { return "login";}
	
	@GetMapping("/register")
	public String register() { return "register";}
	
	@GetMapping("/public")
	public String PUBLIC() { return "public";}
	


}
