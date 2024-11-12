package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.UserDto;

public interface UserService {
	
	//查詢所有使用者
	public List<UserDto> getAllUsers();
	
	//查詢單筆使用者
	public UserDto getUserByAccount(Integer userId);
	
	//新增普通使用者
	public void addUser(UserDto userDto); 
	 
	//新增愛媽使用者
	public void addLoveHomeUser(LovehomeDto lovehomeDto); 
	
	//修改使用者
	public void updateUser(Integer userId, UserDto userDto);
	
	//修改愛媽使用者
	public void updateLoveHomeUser(Integer lovehomeId,LovehomeDto lovehomeDto);
	
	//刪除使用者
	public void deleteUser(Integer userId);
	
	
	//修改密碼
	public void updatePassword(Integer userId,String username, String oldPassword ,String newPassword);
	
	
}
