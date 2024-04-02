package com.port.chairshop.service;



import java.util.List;

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

	public List<CartVO> selectCart(String email) {		
		return cr.selectCart(email);
	}
	
	public void deleteCart(CartVO cartVO) {
		cr.deleteCart(cartVO);
	}
	
	public void minus(CartVO cartVO) {
		cr.minusCart(cartVO);
	}
}
