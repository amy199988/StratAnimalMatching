package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCert {
	private Integer userId; // 使用者ID
	private String username; // 使用者名稱
	private String account; // 會員帳號
	private String role; // 角色權限 
}
