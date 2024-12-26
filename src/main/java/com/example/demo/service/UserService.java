package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UserDto;

public interface UserService {
	

	//新增普通使用者
	public void addUser(UserDto userDto); 
	
	//查詢所有使用者
	public List<UserDto> getAllUsers();
	
	//查詢單筆使用者
	public UserDto getUserById(Integer userId);
	public UserDto getUserByAccount(String account);
	 
	//修改使用者
	public UserDto updateUser(UserDto userDto);
	
	//刪除使用者
	public void deleteUser(Integer userId);
	
	//修改密碼
	public void updatePassword(Integer userId, String oldPassword ,String newPassword);
	
	//根據lovehomeId比對User
	public UserDto  getUserByLovehomeId(Integer lovehomeId);
	
	//發送忘記密碼信件
	public void sendResetPasswordEmail(String account, String email);
	
	//忘記密碼修改密碼
	public void resetPassword(String token,String newPassword);
	
	// 帳號驗證寄信
	public void sendVerificationEmail(String account, String email);
	
	// 使用者Email開通確認
	public void passEmail(String account);

}
