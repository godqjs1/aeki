package com.port.chairshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.port.chairshop.service.UserService;
import com.port.chairshop.vo.UserVO;


@Controller
public class MainController {
	
	@Autowired
	UserService ms;
	
	@GetMapping("/sign")
	public void sign() {		
	}
	@PostMapping("/sign")
	public String signUp(UserVO UserVO) {
		if(ms.insertUser(UserVO) > 0) {
			return "/sign";
		}
		return "/index";
	}
	@GetMapping("/shop")
	public void shop() {		
	}
	
	@GetMapping("/cart")
	public void cart() {		
	}
	
	@GetMapping("/orderList")
	public void orderList() {		
	}
	
	
}
