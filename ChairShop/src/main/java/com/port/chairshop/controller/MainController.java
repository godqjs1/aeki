package com.port.chairshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

	@GetMapping("/sign")
	public void sign() {		
	}
	
	@GetMapping("/cart")
	public void cart() {		
	}
	
}
