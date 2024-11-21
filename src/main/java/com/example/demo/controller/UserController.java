package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

/**
請求方法     URL路徑              功能說明            請求參數                                         回應
-----------------------------------------------------------------------------------------------------------------------------------
GET      /user                   取得會員服務清單         無                                       成功時返回所有房間的列表及成功訊息。
GET      /user/sign_up  	     會員註冊           		無                   				  成功時返回指定房間資料及成功訊息。
POST     /user/add           	  新增會員           請求體包含 userDto,password                             成功時返回成功訊息，無回傳資料。
PUT      /user/{userAccount}     更新指定會員        userAccount (路徑參數，房間 ID)，請求體包含 userDto       成功時返回成功訊息，無回傳資料。
DELETE   /user/{userAccount}     刪除指定會員        userAccount (路徑參數，房間 ID)                       成功時返回成功訊息，無回傳資料。

*/


@RestController
//@RequestMapping({"/user"})
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public String User() {
		return "/"; //會員服務畫面
	}
	
	//註冊
//	@GetMapping("/sign_up")
//	public String sign_up() {
//		return "/User/sign_up";
//	}
	
	@PostMapping("/sign_up")
	public void addUser(@RequestBody UserDto userDto,String password){
		userService.addUser(userDto, password);
	}
	
	
	//修改
	@GetMapping("/{userAccount}")
	public String getUser(@PathVariable("account") String account,Model model) {
		UserDto userDto = userService.getUserByAccount(account);
		model.addAttribute("userDto",userDto);
		return "user_update";
	}
	
//	@PostMapping("/{userAccount}")
//	public void updateUser(@PathVariable String account,@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
//		userService.updateUser(account, userDto);
//		
//		if(bindingResult.hasErrors()) { // 若有錯誤發生
//			model.addAttribute("userDto", userDto); // 將原本的 roomDto 回傳
//			return "user_update"; // 會自動將錯誤訊息傳給 jsp
//		}
//		userService.updateUser(account, userDto);
//		return "redirect:/user"; // 重導到 /rooms 頁面
//	}
//	}
	
}
