package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.User;

public interface UserRepositoryJdbc {
	
	//查詢
	List<User> findAllUsers(); 
	
	//查詢單筆
	Optional<User> findByAccount(Integer account);
	
	//回傳筆數
	int save(User user); 
	
	//新增
	void addUser(User user);
	
	//刪除
	int deleteById(Integer userId); 
	
	// 修改 active 狀態
	void updateUserActive(Integer userId, Boolean active);
	
	//修改密碼
	void updatePasswordHash(Integer userId, String newPasswordHash);
	
}
