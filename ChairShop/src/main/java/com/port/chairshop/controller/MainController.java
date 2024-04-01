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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.port.chairshop.service.CartService;
import com.port.chairshop.service.UserService;
import com.port.chairshop.vo.CartVO;
import com.port.chairshop.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService us;
	
	@Autowired
	CartService cs;
	
	@GetMapping("/index")
	public void index() {
	}
	
	/*=================================================== sign ===================================================*/
	
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
	public String signIn(@Valid UserVO userVO, Errors errors, Model model, HttpServletRequest req, RedirectAttributes redirectAttributes) {
		
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
		
		if (userInfo == null) {
			redirectAttributes.addFlashAttribute("check", 2);
			redirectAttributes.addFlashAttribute("msg", "가입되지 않은 email입니다.");
	    	return "redirect:/sign";
		}
		
		// 암호화되어 데이터베이스에 저장된 비밀번호
		String encodePw = userInfo.getPassword();
		// 로그인시 입력한 비밀번호
		String inputPw = userVO.getPassword();
		
		
		// 암호화된 비밀번호와 입력한 비밀번호를 비교하여 일치하는지 확인
	    boolean passwordMatches = us.matchesBcrypt(inputPw, encodePw);
		
	    HttpSession session = req.getSession();
	    
	    if (passwordMatches) {
	    	
	    	session.setAttribute("user", userInfo);
	    	
	    	return "redirect:/index";
	    }
	    else {
	    	redirectAttributes.addFlashAttribute("check", 2);
	    	redirectAttributes.addFlashAttribute("msg", "비밀번호를 다시 입력하세요.");
	    	return "redirect:/sign";
	    }
		
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		
//		req.getSession(false)는 세션이 이미 존재하면 현재 세션을 반환하고, 
//		세션이 존재하지 않으면 새로운 세션을 생성하지 않고 null을 반환합니다.
//		
//		이렇게 사용하는 이유는 로그아웃 기능과 같이 세션을 삭제하고자 할 때, 
//		세션이 없는 경우에는 삭제할 세션이 없기 때문에 새로운 세션을 생성하지 않도록 하기 위해서입니다.
		
		HttpSession session = req.getSession(false); // 기존 세션이 있으면 가져옴
        if (session != null) {
            session.invalidate(); // 세션을 무효화하여 삭제
        }
        
        return "redirect:/index"; // 로그아웃 후 인덱스 페이지로 리다이렉트
		
	}
  
	/*=================================================== cart ===================================================*/
	
	@GetMapping("/shop")
	public void shop() {
	}

	@GetMapping("/shopCart")
	public String shopCart(
			@RequestParam("email") String email, 
			@RequestParam("product") String product, 
			@RequestParam("price") int price, 
			@RequestParam("img") String img) {
		
		
		logger.info("shopCart 진입");
		
		CartVO cartVO = new CartVO();
		cartVO.setEmail(email);
        cartVO.setProduct(product);
        cartVO.setPrice(price);
        cartVO.setImg(img);
		

        if (cs.productChk(cartVO) > 0) {
            // 이미 상품이 장바구니에 담겨있는 경우
        	cs.addCart(cartVO);
        } else {
        	// 장바구니에 새로 생성
        	cs.insertCart(cartVO);
        }
        
		return "/cart";
		
	}
	
	@GetMapping("/cart")
	public void cart() {
	}

	@GetMapping("/orderList")
	public void orderList() {
	}

}
