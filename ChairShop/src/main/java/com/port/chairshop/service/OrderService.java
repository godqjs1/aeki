package com.port.chairshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.port.chairshop.repository.OrderRepository;
import com.port.chairshop.vo.OrderVO;



@Service
public class OrderService {
	
	@Autowired
	OrderRepository or;
	

	public void insert(String email) {
		or.insert(email);
	}


	public void delete(String email) {
		or.delete(email);
		
	}	

	public List<OrderVO> selectOrder(String email) {		
		return or.selectOrder(email);
	}
	
	public void orderDelete(OrderVO orderVO) {
		or.orderDelete(orderVO);
		
	}
	
	}
	

