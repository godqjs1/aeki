package com.port.chairshop;

import org.apache.ibatis.annotations.Mapper;
import com.port.chairshop.vo.UserVO;

@Mapper
public interface UserMapper {
		
	public int insertUser(UserVO UserVO);		

	public UserVO findByEmail(String email);
	
	public UserVO selectUser(UserVO userVO);
  
}