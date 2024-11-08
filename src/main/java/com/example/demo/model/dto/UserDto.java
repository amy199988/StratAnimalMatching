package com.example.demo.model.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer userId; // 會員編號
	private String username; // 會員名稱
	private String account; // 會員帳號
	private Integer phone; // 電話
	private Date birthdate; // 生日
	private String email; // 電子郵件
	private String address; // 地址
	private Boolean active; // 帳號認證
	private String role; // 帳號權限(普通、愛媽、管理員)
}
