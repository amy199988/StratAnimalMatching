package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.dto.UserDto;

public interface UserService {
	
	//查詢所有使用者
	public List<UserDto> getAllUsers();
	
	//查詢單筆使用者
	public UserDto getUserByAccount(String account);
	
	//新增普通使用者
	public void addUser(Integer userId, String username,String account,String password,Integer phone,Date birthdate,String email,Boolean active,String role); 
	
	//新增愛媽使用者
	public void addLoveHomeUser(Integer userId, String username,String account,String password,Integer phone,Date birthdate,String email,Boolean active,
								String role,String lovehomename,String lovehomeAddress,String contactInfo); 
	
	//修改使用者
	
	
	
	//修改愛媽使用者
	
	
	
	//修改密碼
	
	
	//刪除使用者
	
	
}
