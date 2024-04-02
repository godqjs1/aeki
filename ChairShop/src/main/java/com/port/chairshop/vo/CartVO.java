package com.port.chairshop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {
	
	private String email;
	private String product;
	private int price;
	private String img;
	private int qty;
	
}
