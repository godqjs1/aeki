package com.port.chairshop;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.port.chairshop.vo.OrderVO;

@Mapper
public interface OrderMapper {

	public void insert(String email);

	public void delete(String email);

	public List<OrderVO> selectOrder(String email);

	public void orderDelete(OrderVO orderVO);	
		
}