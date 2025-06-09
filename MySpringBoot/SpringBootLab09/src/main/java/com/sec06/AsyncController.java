package com.sec06;

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
	private AsyncService userService;
	
	@GetMapping("/hello")
	public String hello(@RequestParam String name) {
		userService.processAsync(name);
		return "비동기 처리 완료  : " + name;
	}
}
