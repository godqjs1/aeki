package com.port.chairshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.port.chairshop.service.repository.UserRepository;
import com.port.chairshop.vo.UserVO;

@Service
public class UserService {
		
	@Autowired
	UserRepository mr;
	
	public int insertUser(UserVO UserVO) {
		return mr.insertUser(UserVO);
	}
	
}
