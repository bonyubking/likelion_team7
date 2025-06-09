package com.sec02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {
	
	@Autowired
	private AsyncUserService userService;
	
	@GetMapping("/register")
	public String register(@RequestParam String username) {
		userService.registerUser(username);
		return "User registered  : " + username;
	}
}
