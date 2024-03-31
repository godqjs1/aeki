package com.port.chairshop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.port.chairshop.repository.UserRepository;
import com.port.chairshop.vo.UserVO;

@Service
public class UserService {
		
	@Autowired
	UserRepository ur;
	
	public int insertUser(UserVO UserVO) {
		return ur.insertUser(UserVO);
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
