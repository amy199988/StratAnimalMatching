package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB API
 * --------------------------
 * 一般會員使用
 * Servlet-Patf: /user
 * --------------------------
 * GET   /user             		  查看會員資料
 * PUT   /user/update      		  更新會員資料
 * GET   /user/report 	          查看通報資料
 * GET   /user/request            查看領養申請追蹤
 * POST  /user/password    		  修改密碼送出
 * 
 * 
 * POST  /donation         		  新增捐贈
 * GET   /user/donation    		  捐贈追蹤
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdoptionRequestService adoptionRequestService;
	
	//查看會員資訊
	@GetMapping
	public ResponseEntity<ApiResponse<UserDto>> userSetting(HttpSession session) {
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		UserDto userDto = userService.getUserById(userCert.getUserId());
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",userDto));
	}
	
	//更新會員資料
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto upUserDto, HttpSession session) {
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		UserDto userDto = userService.getUserById(userCert.getUserId());
		userDto = userService.updateUser(upUserDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功",userDto));
	}
	
	//查看通報追蹤
	@GetMapping("/report")
	public ResponseEntity<ApiResponse<List<ReportListDto>>> getUserReportList(HttpSession session) {
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		List<ReportListDto> reportDtos = userService.getUserReportList(userCert.getUserId());
		return ResponseEntity.ok(ApiResponse.success("查詢成功",reportDtos));
	}
	
	//查看領養申請追蹤
	@GetMapping("/request")
	public ResponseEntity<ApiResponse<List<AdoptionRequestDto>>> getUserAdoptionList(@PathVariable Integer userId) {
		List<AdoptionRequestDto> adoptionRequestDtos = adoptionRequestService.getRequestsByuserId(userId);
		return ResponseEntity.ok(ApiResponse.success("查詢成功",adoptionRequestDtos));
	}
	
	//修改密碼
	@PostMapping("/password")
	public ResponseEntity<ApiResponse<String>> updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
    HttpSession session){
		UserCert userCert = (UserCert)session.getAttribute("userCert");
		UserDto userDto = userService.getUserById(userCert.getUserId());
		Integer userId = userCert.getUserId();
		userService.updatePassword(userId, oldPassword, newPassword);
		return ResponseEntity.ok(ApiResponse.success("更新成功",null));
	} 
	
	
}
	
