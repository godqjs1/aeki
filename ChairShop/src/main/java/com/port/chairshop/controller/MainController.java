package com.port.chairshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.port.chairshop.service.UserService;
import com.port.chairshop.vo.UserVO;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService us;

	@GetMapping("/sign")
	public void sign() {
	}

	/*
	 * @PostMapping("/sign") public String signUp(UserVO userVO, RedirectAttributes
	 * redirectAttributes) { // 사용자가 제출한 비밀번호를 BCrypt로 암호화하여 저장 String rawPassword =
	 * userVO.getPassword(); String encryptedPassword =
	 * us.encodeBcrypt(rawPassword); userVO.setPassword(encryptedPassword);
	 * 
	 * 사용자 등록 if (us.insertUser(userVO) > 0) { 회원가입이 성공한 경우 로그를 남기고 회원가입 페이지로 이동
	 * logger.info("User signed up successfully: {}", userVO.getEmail()); return
	 * "redirect:/sign"; } else { 회원가입이 실패한 경우 메인 페이지로 이동
	 * logger.error("Failed to sign up user: {}", userVO.getEmail()); return
	 * "redirect:/index"; } }
	 * 
	 * try { us.insertUser(userVO); logger.info("sign 진입"); return "sign"; // 회원 가입
	 * 성공 시 sign 페이지로 이동 } catch (RuntimeException e) { // 중복된 이메일 주소로 가입 시도 시 에러
	 * 메시지를 추가하고 회원 가입 페이지로 리디렉션
	 * redirectAttributes.addFlashAttribute("errorMessage", e.getMessage()); return
	 * "index"; } }
	 */

	@PostMapping("/sign")
	public String signUp(UserVO userVO, RedirectAttributes redirectAttributes) {
		// 중복된 아이디인지 확인
		if (us.isEmailExists(userVO.getEmail())) {
			// 중복된 아이디인 경우 에러 메시지를 전달하고 회원가입 페이지로 이동
			redirectAttributes.addFlashAttribute("error", "이미 사용 중인 아이디입니다.");
			return "redirect:/sign";
		}

		// 사용자 비밀번호를 BCrypt로 암호화하여 저장
		String rawPw = userVO.getPassword();
		String encodePw = us.encodeBcrypt(rawPw);
		userVO.setPassword(encodePw);

		if (us.insertUser(userVO) > 0) {
			logger.info("sign 진입");
			// 회원가입 성공 시 success 메시지를 전달하고 /success 페이지로 리다이렉트합니다.
			redirectAttributes.addFlashAttribute("success", "회원가입에 성공하였습니다.");
			return "redirect:/sign";
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
