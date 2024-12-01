package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
 * GET   /auth/login      登入頁面
 * POST  /auth/login      登入
 * GET   /auth/logout     登出
 * GET   /auth/sign_up    註冊表單
 * POST  /auth /sign_up    註冊
 * 
 * 
 * POST /forget_password 忘記密碼 
 */


//@RestControlle
@Controller
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class AuthController{

	@Autowired
	private CertService certService;
	
	@Autowired
	private UserService userService;

	//登入頁面
	@GetMapping("/login")
	public String loginPage() {
		return "User/login";
	}
	//註冊頁面
	@GetMapping("/sign_up") 
	public String sign_up() {
		return "User/sign_up"; 
	}
	

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<String>> login(@RequestParam("account") String account, @RequestParam("password") String password,
            HttpSession session) {
		//取得憑證
		UserCert userCert =  certService.getCert(account, password);
		//放入憑證
		session.setAttribute("userCert", userCert);
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
	}
	
	//登出
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));
		
	}
	
	@PostMapping("/sign_up")
	public ResponseEntity<ApiResponse<String>> addUser(@RequestBody UserDto userDto,String password){
		userService.addUser(userDto, password);
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	}
	
}
