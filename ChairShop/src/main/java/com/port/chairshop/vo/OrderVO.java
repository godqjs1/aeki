package com.port.chairshop.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {
			
		private String email;
		private String product;	
		private int price;
		private String img;
		private int qty;
		private Date orderDate;
			
}
