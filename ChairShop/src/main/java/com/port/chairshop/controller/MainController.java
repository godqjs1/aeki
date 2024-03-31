package com.port.chairshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.port.chairshop.service.UserService;
import com.port.chairshop.vo.UserVO;

import jakarta.validation.Valid;


@Controller
public class MainController {
	
	@Autowired
	UserService us;
	
	@GetMapping("/sign")
	public void sign(Model model) {
		UserVO userVO = new UserVO(); // UserVO 객체 초기화
		model.addAttribute("user", userVO);
	}
	
	@PostMapping("/sign")
	public String signUp(UserVO UserVO) {
		
		if(us.insertUser(UserVO) > 0) {
			return "/sign";
		}
		
		return "/index";
	}
	
	@PostMapping("/signIn")
	public String signIn(@Valid UserVO UserVO, Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			/* 로그인 실패시 입력 데이터 값을 유지 */
			model.addAttribute("user", UserVO);
			
			/* 유효성 통과 못한 필드와 메시지를 핸들링 */
			Map<String, String> validatorResult = us.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			/* 로그인 페이지로 다시 리턴 */
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
