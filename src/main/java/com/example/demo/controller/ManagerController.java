package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aop.CheckRole;
import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.service.LovehomeService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;

/*
 * WEB API
 * --------------------------
 * 管理員使用
 * Servlet-Patf: /manager
 * --------------------------
 * GET    /manager                             管理員服務頁面+所有會員列表
 * GET    /manager/all_cat                     查看所有貓咪列表
 * GET    /manager/all_lovehome                查看所有中途會員列表
 * GET    /manager/all_request				   查看所有申請列表
 * GET    /manager/all_report                  查看所有通報列表
 * PUT    /manager/{userId}          		   修改會員資料送出
 * PUT    /manager/all_cat/{catId}             修改貓咪資料送出
 * PUT    /manager/all_lovehome/{lovehomeId}   修改中途資料送出
 * PUT    /manager/all_request/{requestNumber} 修改申請資料送出
 * PUT    /manager/all_report/{reportNumber}   修改通報資料送出
 * DELETE /manager/{userId}          		   刪除會員資料
 * DELETE /manager/all_cat/{catId}             刪除貓咪資料
 * DELETE /manager/all_request/{requestNumber} 刪除申請資料
 * DELETE /manager/all_report/{reportNumber}   刪除通報資料
 * 
 *
 * GET   /manager/all_donation         查看所有捐贈
 */

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ManagerController {

	@Autowired
	private UserService userService;

	@Autowired
	private LovehomeService lovehomeService;

	@Autowired
	private AdoptionCatService adoptionCatService;

	@Autowired
	private AdoptionRequestService adoptionRequestService;

	@Autowired
	private ReportService reportService;

	// 查詢所有使用者
	@GetMapping
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<List<UserDto>>> findAllUsers() {
		List<UserDto> userDtos = userService.getAllUsers();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", userDtos));
	}

	// 查詢所有中途會員
	@GetMapping("/all_lovehome")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<List<LovehomeDto>>> findAllLovehomes() {
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", lovehomeDtos));
	}

	// 查詢所有貓咪列表
	@GetMapping("/all_cat")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<List<CatDto>>> findAllCats() {
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", catDtos));
	}

	// 查詢所有申請列表
	@GetMapping("/all_request")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<List<AdoptionRequestDto>>> findAllRequests() {
		List<AdoptionRequestDto> requestDtos = adoptionRequestService.findAllRequests();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", requestDtos));
	}

	// 查詢所有通報列表
	@GetMapping("/all_report")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<List<ReportListDto>>> findAllReports() {
		List<ReportListDto> reportDtos = reportService.getAllReport();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", reportDtos));
	}

	// 更新使用者
	@PutMapping("/{userId}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto) {
		UserDto updateUserDto = userService.updateUser(userDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", updateUserDto));
	}

	// 刪除使用者
	@DeleteMapping("/{userId}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}

	// 修改中途資料
	@PutMapping("/all_lovehome/{lovehomeId}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(@PathVariable Integer lovehomeId,
			@RequestBody LovehomeDto lovehomeDto) {
		lovehomeDto = lovehomeService.updateLovehome(lovehomeDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", lovehomeDto));
	}

	// 修改貓咪資料
	@PutMapping("/all_cat/{catId}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId, CatDto catDto) {
		catDto = adoptionCatService.updateCat(catDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", catDto));
	}

	// 刪除貓咪
	@DeleteMapping("/all_cat/{catId}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<String>> deleteCat(@PathVariable Integer catId) {
		adoptionCatService.deleteCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}

	// 修改申請資料
	@PutMapping("/all_request/{requestNumber}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<AdoptionRequestDto>> updateRequest(@RequestBody AdoptionRequestDto adoptionRequestDto) {
		AdoptionRequestDto updateRequestDto = adoptionRequestService.updateAdoptionRequest(adoptionRequestDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", updateRequestDto));
	}

	// 刪除申請
	@DeleteMapping("/all_request/{requestNumber}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<String>> deleteRequest(@PathVariable Integer requestNumber) {
		adoptionRequestService.deleteRequestByNumber(requestNumber);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}

	// 修改通報資料
	@PutMapping("/all_report/{reportNumber}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<ReportListDto>> updateReport(@RequestBody ReportListDto reportDto) {
		ReportListDto updateReportDto = reportService.updateReport(reportDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", updateReportDto));
	}

	// 刪除通報
	@DeleteMapping("/all_report/{reportNumber}")
	@CheckRole({"role_manager"})
	public ResponseEntity<ApiResponse<String>> deleteReport(@PathVariable Integer reportNumber) {
		reportService.deleteReport(reportNumber);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}

}
