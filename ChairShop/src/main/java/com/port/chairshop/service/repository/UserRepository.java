package com.port.chairshop.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.port.chairshop.UserMapper;
import com.port.chairshop.vo.UserVO;

@Repository
public class UserRepository {

	@Autowired
	UserMapper mapper;
	
	public int insertUser(UserVO UserVO) {
		return mapper.insertUser(UserVO);
	}
	
}
