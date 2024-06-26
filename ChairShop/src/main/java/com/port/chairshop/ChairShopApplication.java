package com.port.chairshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(value = {"com.port.chairshop"})
public class ChairShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChairShopApplication.class, args);
	}

}
