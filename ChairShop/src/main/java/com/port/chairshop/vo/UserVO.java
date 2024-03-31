package com.port.chairshop.vo;  

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Getter  
@Setter  
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	  
	private String name;
	  
	@NotBlank(message = "Email을 입력해주세요.")
  
	private String email;
  
	@NotBlank(message = "Password를 입력해주세요.")
	private String password;
	  
}
 