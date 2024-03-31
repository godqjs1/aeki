package com.port.chairshop.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.port.chairshop.service.UserService;
import com.port.chairshop.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService us;
  
	@GetMapping("/sign")
	public String sign(Model model) {
		UserVO userVO = new UserVO(); // UserVO 객체 초기화
		model.addAttribute("user", userVO);
		return "/sign";
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid UserVO userVO, Errors errors, Model model, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors()) {
			/* 로그인 실패시 입력 데이터 값을 유지 */
			model.addAttribute("user", userVO);
			
			/* 유효성 통과 못한 필드와 메시지를 핸들링 */
			Map<String, String> validatorResult = us.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			model.addAttribute("signup", "fail");
			
			/* 로그인 페이지로 다시 리턴 */
			return "/sign";
		}
		// 중복된 아이디인지 확인
		if (us.isEmailExists(userVO.getEmail())) {
			// 중복된 아이디인 경우 에러 메시지를 전달하고 회원가입 페이지로 이동
			redirectAttributes.addFlashAttribute("error", "이미 사용 중인 Email입니다.");
			redirectAttributes.addFlashAttribute("signup", "fail"); 
			return "redirect:/sign";
		}

		// 사용자 비밀번호를 BCrypt로 암호화하여 저장
		String rawPw = userVO.getPassword();
		String encodePw = us.encodeBcrypt(rawPw);
		userVO.setPassword(encodePw);
		
		redirectAttributes.addFlashAttribute("check", 2);
    
		if (us.insertUser(userVO) > 0) {
			logger.info("sign 진입");			
			logger.info("check 진입");
			// 회원가입 성공 시 success 메시지를 전달하고 /success 페이지로 리다이렉트합니다.
			redirectAttributes.addFlashAttribute("msg","회원가입이 완료되었습니다.");
			
			
			return "redirect:/sign";
		} else {
			redirectAttributes.addFlashAttribute("signup", "fail");
			redirectAttributes.addFlashAttribute("msg","회원가입에 실패하였습니다.");
		}
		
		
		return "redirect:/sign";
	}		
	
	@PostMapping("/signIn")
	public String signIn(@Valid UserVO userVO, Errors errors, Model model, HttpServletRequest req) {
		
		if (errors.hasErrors()) {
			/* 로그인 실패시 입력 데이터 값을 유지 */
			model.addAttribute("user", userVO);
			
			/* 유효성 통과 못한 필드와 메시지를 핸들링 */
			Map<String, String> validatorResult = us.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			/* 로그인 페이지로 다시 리턴 */
			return "/sign";
		}
		
		UserVO userInfo = us.selectUser(userVO); 
		
		// 암호화되어 데이터베이스에 저장된 비밀번호
		String encodePw = userInfo.getPassword();
		// 로그인시 입력한 비밀번호
		String inputPw = userVO.getPassword();
		
		// 암호화된 비밀번호와 입력한 비밀번호를 비교하여 일치하는지 확인
	    boolean passwordMatches = us.matchesBcrypt(inputPw, encodePw);
		
	    HttpSession session = req.getSession();
	    
	    if (passwordMatches) {
	    	
	    	session.setAttribute("user", userInfo);
	    	
	    	return "/index";
	    }
	    else {
	    	return "/sign";
	    }
		
		
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
