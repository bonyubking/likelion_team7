package com.sec03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class CustomEventController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public String createOrder(@RequestParam String productName, @RequestParam String email) {
		orderService.createOrder(productName, email);
		return email + " 님의 " + productName + "주문이 들어왔습니다.";
	}
}
