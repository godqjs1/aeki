package com.port.chairshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.port.chairshop.OrderMapper;
import com.port.chairshop.vo.OrderVO;

@Repository
public class OrderRepository {

	@Autowired
	OrderMapper mapper;
	
	public List<OrderVO> selectOrder(String email) {
		return mapper.selectOrder(email);
	};
	
	public void insert(String email) {
		mapper.insert(email);
		
	}

	public void delete(String email) {
		mapper.delete(email);
		
	}

	public void orderDelete(OrderVO orderVO) {
		mapper.orderDelete(orderVO);
		
	}

}
