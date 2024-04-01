package com.port.chairshop.repository;

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
}
