package com.port.chairshop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.port.chairshop.CartMapper;
import com.port.chairshop.vo.CartVO;

@Repository
public class CartRepository {

	@Autowired
	CartMapper mapper;
	
	public int productChk(CartVO cartVO) {
		return mapper.productChk(cartVO);
	}
	
	public void insertCart(CartVO cartVO) {
		mapper.insertCart(cartVO);
	}
	
	public void addCart(CartVO cartVO) {
		mapper.addCart(cartVO);
	}
	
	public List<CartVO> selectCart(String email) {		
		return mapper.selectCart(email);		
	}
	
	public void deleteCart(CartVO cartVO) {
		mapper.deleteCart(cartVO);
	}
	
	public void minusCart(CartVO cartVO) {
		mapper.minusCart(cartVO);
	}
	
}
