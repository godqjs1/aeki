package com.port.chairshop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.port.chairshop.vo.CartVO;


@Mapper
public interface CartMapper {
		
	public int productChk(CartVO cartVO);	
	
	public void insertCart(CartVO cartVO);
	
	public void addCart(CartVO cartVO);  	
	
	public List<CartVO> selectCart(String email);
	
	public void deleteCart(CartVO cartVO);
	
	public void minusCart(CartVO cartVO);
	
}