package com.port.chairshop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.port.chairshop.repository.UserRepository;
import com.port.chairshop.vo.UserVO;

@Service
public class UserService {
  		
	@Autowired
	UserRepository ur;
	

	public int insertUser(UserVO userVO) {
	        // 회원 가입 전에 이메일 중복을 확인
	        if (ur.findByEmail(userVO.getEmail()) != null) {
	            throw new RuntimeException("중복된 이메일 주소입니다.");
	        }
	        return ur.insertUser(userVO);
	    }
	
	 // 이메일이 이미 데이터베이스에 존재하는지 확인하는 메서드
	    public boolean isEmailExists(String email) {
	        // 이메일이 이미 데이터베이스에 존재하는지 확인하여 결과를 반환합니다.
	        // 존재하면 true, 존재하지 않으면 false를 반환합니다.
	        return ur.findByEmail(email) != null;
	    }
	
	/*
	 * public String encodeBcrypt(String planeText, int strength) { return new
	 * BCryptPasswordEncoder(strength).encode(planeText); }
	 */
	
	public String encodeBcrypt(String plainText) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(plainText);
	}			 		
	
	/* 로그인 시, 유효성 체크 */
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		return validatorResult;
	}
}
