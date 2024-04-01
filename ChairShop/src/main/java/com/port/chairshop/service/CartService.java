package com.port.chairshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.port.chairshop.repository.CartRepository;
import com.port.chairshop.vo.CartVO;

@Service
public class CartService {

	@Autowired
	CartRepository cr;
	
	public int productChk(CartVO cartVO) {
		return cr.productChk(cartVO);
	}
	
	public void insertCart(CartVO cartVO) {
		cr.insertCart(cartVO);
	}
	
	public void addCart(CartVO cartVO) {
		cr.addCart(cartVO);
	}
}
