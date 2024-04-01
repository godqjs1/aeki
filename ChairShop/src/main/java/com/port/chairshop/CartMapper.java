package com.port.chairshop;

import org.apache.ibatis.annotations.Mapper;

import com.port.chairshop.vo.CartVO;

@Mapper
public interface CartMapper {
		
	public int productChk(CartVO cartVO);	
	
	public void insertCart(CartVO cartVO);
	
	public void addCart(CartVO cartVO);
  
}