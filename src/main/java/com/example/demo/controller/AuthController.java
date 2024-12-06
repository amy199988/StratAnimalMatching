package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.CertService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


/*
 * WEB API
 * --------------------------
 * Servlet-Patf: /auth
 * -------------------------- 
 * POST  /auth/login      登入
 * GET   /auth/logout     登出
 * POST  /auth/sign_up    註冊
 * 
 * 
 * POST /forget_password 忘記密碼 
 */


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class AuthController{

	@Autowired
	private CertService certService;
	
	@Autowired
	private UserService userService;
	
	//登入
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserDto>> login(@RequestParam("account") String account, @RequestParam("password") String password,
            HttpSession session) {
		//取得憑證
		UserCert userCert =  certService.getCert(account, password);
		//放入憑證
		session.setAttribute("userCert", userCert);
		UserDto userDto = userService.getUserById(userCert.getUserId());
		return ResponseEntity.ok(ApiResponse.success("登入成功", userDto));
	}
	
	//登出
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));
		
	}
	
	// 註冊
	@PostMapping("/sign_up")
	public ResponseEntity<ApiResponse<String>> addUser(@RequestBody UserDto userDto){
		userService.addUser(userDto);
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	}
	
}
