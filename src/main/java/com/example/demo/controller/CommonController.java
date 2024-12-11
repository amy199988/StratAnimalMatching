package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.service.LovehomeService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB API
 * --------------------------
 * 一般大眾皆可使用，不需要登入的
 * Servlet-Patf: /common
 * --------------------------
 * GET  /common/adoption                 查看所有可領養貓咪
 * POST /common/adoption_request         領養貓咪清單送出
 * GET  /common/lovehome_list            查看所有中途之家
 * 
 * 
 * GET /donation         捐贈表單
 * POST/donation         新增捐贈
 * GET /donation_list    捐贈表揚
 */

@RestController
@RequestMapping("/common")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CommonController {

	@Autowired
	private AdoptionCatService adoptionCatService;

	@Autowired
	private LovehomeService lovehomeService;

	@Autowired
	private AdoptionRequestService adoptionRequestService;
	
	@Autowired
	private UserService userService;

	// 查詢所有可領養貓咪
	@GetMapping("/adoption")
	public ResponseEntity<ApiResponse<List<CatDto>>> allAdoption() {
		List<CatDto> catDtos = adoptionCatService.findAllAdoptionCats();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", catDtos));
	}

	// 查看所有中途之家
	@GetMapping("/lovehome_list")
	public ResponseEntity<ApiResponse<List<LovehomeDto>>> lovehomeList() {
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", lovehomeDtos));
	}

	// 申請領養
	@PostMapping("/adoption_request")
	public ResponseEntity<ApiResponse<AdoptionRequestDto>> UserAdoptionRequest(HttpSession session,
			@RequestBody AdoptionRequestDto adoptionRequestDto) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		UserDto userDto = userService.getUserById(userCert.getUserId());
		adoptionRequestDto.setUserDto(userDto);
		AdoptionRequestDto addAdoptionRequestDto = adoptionRequestService.addAdoptionRequest(adoptionRequestDto);
		return ResponseEntity.ok(ApiResponse.success("申請成功", addAdoptionRequestDto));
	}

}
