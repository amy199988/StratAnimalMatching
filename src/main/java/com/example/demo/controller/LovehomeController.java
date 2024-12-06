package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.service.LovehomeService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB API
 * --------------------------
 * 中途之家使用
 * Servlet-Patf: /lovehome
 * --------------------------
 * GET    /lovehome               		   	查看愛媽與中途資料
 * PUT	  /lovehome/upuser    		   	    更新愛媽資料
 * PUT    /lovehome/uplovehome    		   	更新中途資料
 * GET    /lovehome/cat_list  		   		查看所有上傳貓咪
 * POST   /lovehome/cat_list          		貓咪新增資料送出
 * PUT    /lovehome/cat_list/{catId}  		貓咪資訊修改送出
 * DELETE /lovehome/cat_list/{catId}        刪除貓咪
 * GET    /lovehome/report    		        查看收到的通報列表
 * GET    /lovehome/report/{reportNumber}   查看個別通報資料
 * PUT    /lovehome/report/{reportNumber}   修改個別通報資料送出
 * GET    /lovehome/request                 查看收到的領養清單
 * GET    /lovehome/request/{requestNumber} 查看個別領養清單
 * PUT    /lovehome/request/{requestNumber} 修改個別領養清單
 */

@RestController
@RequestMapping("/lovehome")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LovehomeController {

	@Autowired
	private LovehomeService lovehomeService;

	@Autowired
	private UserService userService;

	@Autowired
	private AdoptionCatService adoptionCatService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private AdoptionRequestService adoptionRequestService;

	// 查看愛媽資料與中途資訊
	@GetMapping
	public ResponseEntity<ApiResponse<UserDto>> lovehomeSetting(HttpSession session) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		UserDto userDto = userService.getUserById(userCert.getUserId());
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功", userDto));
	}

	// 更新中途資料
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(HttpSession session,
			@RequestBody LovehomeDto upLovehomeDto) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		LovehomeDto lovehomeDto = userCert.getLovehomeDto();
		lovehomeDto = lovehomeService.updateLovehome(upLovehomeDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", lovehomeDto));
	}

	// 新增貓咪
	@PostMapping("/cat_list")
	public ResponseEntity<ApiResponse<CatDto>> appendCat(@RequestBody CatDto catDto, HttpSession session) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		CatDto addCatDto = adoptionCatService.addCat(catDto, userCert.getLovehomeDto().getLovehomeId());
		return ResponseEntity.ok(ApiResponse.success("新增成功", addCatDto));
	}

	// 查看中途所擁有貓咪
	@GetMapping("/cat_list")
	public ResponseEntity<ApiResponse<List<CatDto>>> getLovehomecatList(HttpSession session) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		LovehomeDto lovehomeDto = userCert.getLovehomeDto();
		List<CatDto> catDtos = lovehomeService.getLovehomecatList(lovehomeDto.getLovehomeId());
		return ResponseEntity.ok(ApiResponse.success("獲取所有貓咪", catDtos));
	}

	// 更新貓咪
	@PutMapping("/cat_list/{catId}")
	public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId,@RequestBody CatDto catDto) {
		catDto = adoptionCatService.updateCat(catDto);
		return ResponseEntity.ok(ApiResponse.success("更新成功", catDto));
	}

	// 刪除貓咪
	@DeleteMapping("/cat_list/{catId}")
	public ResponseEntity<ApiResponse<Void>> deleteCat(@PathVariable Integer catId) {
		adoptionCatService.deleteCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}

	// 查看收到的通報列表
	@GetMapping("/report")
	public ResponseEntity<ApiResponse<List<ReportListDto>>> getLovehomeReportList(HttpSession session) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		LovehomeDto lovehomeDto = userCert.getLovehomeDto();
		List<ReportListDto> reportListDtos = lovehomeService.getLovehomeReportList(lovehomeDto.getLovehomeId());
		return ResponseEntity.ok(ApiResponse.success("獲取上傳的通報救援", reportListDtos));
	}

	// 查看個別通報資料
	@GetMapping("/report/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportListDto>> getReport(@PathVariable Integer reportNumber) {
		ReportListDto reportDto = reportService.getReportByNumber(reportNumber);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功", reportDto));
	}

	// 修改個別通報資料送出
	@PutMapping("/report/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportListDto>> updateReport(@RequestBody ReportListDto reportDto) {
		ReportListDto updateReportDto = reportService.updateReport(reportDto);
		return ResponseEntity.ok(ApiResponse.success("修改成功", updateReportDto));
	}

	// 查看申請領養的表單
	@GetMapping("/request")
	public ResponseEntity<ApiResponse<List<AdoptionRequestDto>>> getAdoptionRequestList(HttpSession session) {
		UserCert userCert = (UserCert) session.getAttribute("userCert");
		LovehomeDto lovehomeDto = userCert.getLovehomeDto();
		List<AdoptionRequestDto> adoptionRequestDtos = adoptionRequestService
				.getRequestsBylovehomeId(lovehomeDto.getLovehomeId());
		return ResponseEntity.ok(ApiResponse.success("獲取申請領養的清單", adoptionRequestDtos));
	}

	// 查看個別領養清單
	@GetMapping("/request/{requestNumber}")
	public ResponseEntity<ApiResponse<AdoptionRequestDto>> getRequest(@PathVariable Integer requestNumber) {
		AdoptionRequestDto adoptionRequestDto = adoptionRequestService.getRequestByNumber(requestNumber);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功", adoptionRequestDto));
	}

	// 修改個別領養清單送出
	@PutMapping("/request/{requestNumber}")
	public ResponseEntity<ApiResponse<AdoptionRequestDto>> updateRequest(
			@RequestBody AdoptionRequestDto adoptionRequestDto) {
		AdoptionRequestDto upadoptionRequestDto = adoptionRequestService.updateAdoptionRequest(adoptionRequestDto);
		return ResponseEntity.ok(ApiResponse.success("修改成功", upadoptionRequestDto));
	}
}
