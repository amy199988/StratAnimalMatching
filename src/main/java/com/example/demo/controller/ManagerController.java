package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.LovehomeService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB API
 * --------------------------
 * 管理員使用
 * Servlet-Patf: /manager
 * --------------------------
 * GET   /manager                       管理員服務頁面
 * GET   /manager/all_user              查看所有會員列表
 * GET   /manager/all_lovehome          查看所有中途會員列表
 * GET   /manager/all_cat               查看所有貓咪列表
 * GET   /manager/all_report            查看所有通報列表
 * GET   /manager/update_user/{userId}  修改會員資料頁面
 * PUT   /manager/update_user/{userId}  修改會員資料送出
 * DELETE /manager/delete_user/{userId} 刪除會員
 * GET   /manager/update_lovehome/{lovehomeId}  修改中途資料頁面
 * PUT   /manager/update_lovehome/{lovehomeId}  修改中途資料送出
 * GET   /manager/update_cat/{catId}            修改貓咪資料頁面
 * PUT   /manager/update_cat/{catId}            修改貓咪資料送出
 * DELETE /manager/delete_cat/{catId}           刪除貓咪資料
 * GET   /manager/update_report/{reportNumber}  修改通報資料頁面
 * PUT   /manager/update_report/{reportNumber}  修改通報資料送出
 * DELETE /manager/delete_report/{reportNumber} 刪除通報資料
 * 
 *
 * GET   /manager/all_donation         查看所有捐贈
 */


//@RestController
@Controller
@RequestMapping("/manager")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class ManagerController {

	@Autowired
	private UserService userService;

	@Autowired
	private LovehomeService lovehomeService;
	
	@Autowired
	private AdoptionCatService adoptionCatService;
	
	@Autowired
	private ReportService reportService;

/*
	// 管理員頁面
	@GetMapping
	public String manager() {
		return "/Manager/manager";
	}

	// 貓咪管理頁面
	@GetMapping("/Allcat_list")
	public String Allcat_list() {
		return "/Manager/Allcat_list";
	}

	// 查看所有捐贈名單
*/
	
	// 查詢所有使用者
	@GetMapping("/all_user")
	public ResponseEntity<ApiResponse<List<UserDto>>> findAllUsers() {
		List<UserDto> userDtos = userService.getAllUsers();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", userDtos));
	}
	
	// 查詢所有中途會員
	@GetMapping("/all_lovehome")
	public ResponseEntity<ApiResponse<List<LovehomeDto>>> findAllLovehomes() {
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", lovehomeDtos));
	}
	
	// 查詢所有貓咪列表
	@GetMapping("/all_cat")
	public ResponseEntity<ApiResponse<List<CatDto>>> findAllCats() {
		List<CatDto> catDtos = adoptionCatService.findAllAdoptionCats();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", catDtos));
	}	
	
	// 查詢所有通報列表
	@GetMapping("/all_report")
	public ResponseEntity<ApiResponse<List<ReportListDto>>> findAllReports() {
		List<ReportListDto> reportDtos = reportService.getAllReport();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", reportDtos));
	}
	
	//修改會員資料
	@GetMapping("/update_user/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Integer userId) {
		UserDto userDto = userService.getUserById(userId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",userDto));
	}
	
	@PutMapping("/update_user/{userId}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto) {
		UserDto updateUserDto = userService.updateUser(userDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功",userDto));
	}	
	
	// 刪除使用者
	@DeleteMapping("/delete_user/{userId}")
	public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}	
	
	//修改中途資料
	@GetMapping("/update_lovehome/{lovehomeId}")
	public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(@PathVariable Integer lovehomeId) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",lovehomeDto));
	}
	
	@PostMapping("/update_lovehome/{lovehomeId}")
	public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(@PathVariable Integer lovehomeId,@RequestBody LovehomeDto lovehomeDto,
			@RequestParam("photo") MultipartFile photoFile) {
		if (photoFile == null || photoFile.isEmpty()) {
			lovehomeDto = lovehomeService.updateLovehomeWithoutPhoto(lovehomeDto);
			return ResponseEntity.ok(ApiResponse.success("更新成功",lovehomeDto));
		}
		lovehomeDto = lovehomeService.updateLovehome(lovehomeDto, photoFile);
		return ResponseEntity.ok(ApiResponse.success("更新成功",lovehomeDto));
	}
	
	//修改貓咪資料
	@GetMapping("/update_cat/{catId}")
	public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId) {
		CatDto catDto = adoptionCatService.getCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",catDto));
	}
	
	@PostMapping("/update_cat/{catId}")
	public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId, CatDto catDto,
			@RequestParam("photo") MultipartFile photoFile) {
		if (photoFile == null || photoFile.isEmpty()) {
			catDto = adoptionCatService.updateCatWithoutPhoto(catDto);
			return ResponseEntity.ok(ApiResponse.success("更新成功",catDto));
		}
		catDto = adoptionCatService.updateCat(catDto, photoFile);
		return ResponseEntity.ok(ApiResponse.success("更新成功",catDto));
	}	
	
	// 刪除貓咪
	@DeleteMapping("/delete_cat/{catId}")
	public ResponseEntity<ApiResponse<String>> deleteCat(@PathVariable Integer catId) {
		adoptionCatService.deleteCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}	
	
	//修改通報資料
	@GetMapping("/update_report/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportListDto>> updateReport(@PathVariable Integer reportNumber) {
		ReportListDto reportDto = reportService.getReportByNumber(reportNumber);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",reportDto));
	}
	
	@PutMapping("/update_report/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportListDto>> updateReport(@RequestBody ReportListDto reportDto) {
		ReportListDto updateReportDto = reportService.updateReport(reportDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功",updateReportDto));
	}	
	
	// 刪除通報
	@DeleteMapping("/delete_report/{reportNumber}")
	public ResponseEntity<ApiResponse<String>> deleteReport(@PathVariable Integer reportNumber) {
		reportService.deleteReport(reportNumber);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}	
	
}
