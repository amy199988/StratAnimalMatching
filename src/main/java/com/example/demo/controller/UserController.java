package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/*
 * WEB API
 * --------------------------
 * 一般會員使用
 * Servlet-Patf: /user
 * --------------------------
 * GET   /user                      會員服務頁面
 * GET   /user/setting/{userId}     查看會員資料
 * GET   /user/update/{userId}      更新會員資料頁面
 * PUT   /user/update/{userId}      更新會員資料
 * GET   /user/report               貓咪通報頁面
 * POST  /user/report               通報資料新增
 * GET   /user/report_track/{userId} 查看通報資料
 * GET   /user/adoption              申請領養
 * *POST  /user/adoption              領養申請資料送出
 * *GET   /user/adoption_track/{userId} 查看領養申請追蹤
 * GET   /user/update/password         修改密碼
 * POST  /user/update/password         修改密碼送出
 * 
 * 
 * GET   /user/donation         捐贈表單
 * POST  /user/donation         新增捐贈
 * GET   /user/donation_track/{userId}    捐贈追蹤
 */

//@RestControlle
@Controller
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AdoptionCatService adoptionCatService;
	
	@Autowired
	private ReportService reportService;
	
/*	
	@GetMapping //會員服務畫面
	public String User() {
		return "User/user"; 
	}

	//會員資訊
	@GetMapping("/user/setting")
	public String lovehomeSetting(@RequestParam String account, Model model) {
		UserDto userDto = userService.getUserByAccount(account);
		model.addAttribute("userDto", userDto);
		return "/User/user_setting";
	}
	
	//會員資料修改
	@GetMapping("/{userAccount}")
	public String getUser(@PathVariable("account") String account,Model model) {
		UserDto userDto = userService.getUserByAccount(account);
		model.addAttribute("userDto",userDto);
		return "user_update";
	}
	
	@PostMapping("/{userAccount}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable String account,@RequestBody UserDto userDto) {
		userDto.setAccount(account);
		UserDto updateUserDto = userService.updateUser(userDto);
		return ResponseEntity.ok(ApiResponse.success("修改成功", updateUserDto));
	}
	
	//申請領養
	@GetMapping("/adoptionrequest")
	public String adoptioncat() {
		return "adoptionrequest";
	}

	//通報流浪貓頁面
	@GetMapping("/report")
	public String report() {
		return "report";
	}
	
	//通報頁面送出
	//查詢通報頁面
	
	
*/
	
	//查看會員資訊
	@GetMapping("/setting/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> userSetting(@PathVariable Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",userDto));
	}
	
	//更新會員資料
	@GetMapping("/update/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",userDto));
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto) {
		UserDto updateUserDto = userService.updateUser(userDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功",userDto));
	}
	
	//通報資料新增
	@PostMapping("/report")
	public ResponseEntity<ApiResponse<ReportDto>> addReport(ReportDto reportDto){
		ReportDto addReportDto = reportService.addReport(reportDto);
		return ResponseEntity.ok(ApiResponse.success("新增成功",addReportDto));
	}
	
	//查看通報追蹤
	@GetMapping("pathort_track/{userId}")
	public ResponseEntity<ApiResponse<List<ReportDto>>> getUserReportList(@PathVariable Integer userId) {
		List<ReportDto> reportDtos = userService.getUserReportList(userId);
		return ResponseEntity.ok(ApiResponse.success("查詢成功",reportDtos));
	}
	
	//申請領養
//	@PostMapping("/adoption")
//	public ResponseEntity<ApiResponse<AdoptionRequestDto>> UserAdoptionRequest(@RequestBody AdoptionRequestDto adoptionRequestDto) {
//		AdoptionRequestDto addAdoptionRequestDto = 
//		return ResponseEntity.ok(ApiResponse.success("申請成功",addAdoptionRequestDto));
//	}
	
	//查看領養申請追蹤
//	@GetMapping("/adoption_track/{userId}")
//	public ResponseEntity<ApiResponse<List<AdoptionRequestDto>>> getUserAdoptionList(@PathVariable Integer userId) {
//		List<AdoptionRequestDto> adoptionRequestDtos = 
//		return ResponseEntity.ok(ApiResponse.success("查詢成功",adoptionRequestDtos));
//	}
	
	//修改密碼
	@PostMapping
	public ResponseEntity<ApiResponse<String>> updatePassword(String account, String oldPassword, String newPassword){
		userService.updatePassword(account, oldPassword, newPassword);
		return ResponseEntity.ok(ApiResponse.success("更新成功",null));
	} 
	
}
	
