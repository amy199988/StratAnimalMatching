package com.example.demo.model.entity;

import java.sql.Date;
import lombok.Data;

// 代表 users 資料表的紀錄

@Data
public class User {
	private Integer userId; // 會員編號
	private String username; // 會員名稱
	private String account; // 會員帳號
	private String passwordHash; // 會員Hash密碼
	private String salt; // 隨機鹽
	private String IDcard; // 身分證
	private Integer phone; // 電話
	private Date birthdate; // 生日
	private String email; // 電子郵件
	private String address; // 地址
	private Boolean active; // 帳號認證
	private String role; // 帳號權限(普通、愛媽、管理員)
}
