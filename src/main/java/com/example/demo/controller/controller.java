package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.LovehomeService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB API
 * --------------------------
 * 一般大眾皆可使用，不需要登入的
 * Servlet-Patf: /common
 * --------------------------
 * GET /common/home             首頁
 * GET /common/cat_list         查看所有貓咪
 * GET /common/cat_alladoption  查看所有可領養貓咪
 * GET /common/lomehome_list    查看所有中途之家
 * GET /common/cat/{catId}      查看個別貓的狀態
 * 
 * 
 * GET /donation         捐贈表單
 * POST/donation         新增捐贈
 * GET /donation_list    捐贈表揚
 */

//@RestController
@Controller
@RequestMapping("/common")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class controller {

	@Autowired
	private AdoptionCatService adoptionCatService;
	
	@Autowired
	private LovehomeService lovehomeService;

	
/**	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	

	// 查詢所有貓咪
	@GetMapping("/cat_list")
	public String catlist(HttpSession session) {
		if (session == null) {
			System.out.println("Session is null");
		}
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		session.setAttribute("catDtos", catDtos);
		return "/AdoptionCat/cat_list";
	}
	
	@GetMapping("/adoption")
	public String adoption(HttpSession session) {
	if (session == null) {
		System.out.println("Session is null");
		}
		List<CatDto> catDtos = adoptionCatService.findAllAdoptionCats();
		session.setAttribute("catDtos", catDtos);
		return "/AdoptionCat/adoption";
		}
	
	//捐贈
	@GetMapping("/donation")
	public String donation() {
		return "donation";
	}
*/
	
	// 查看所有貓咪
	@GetMapping("/cat_list")
	public ResponseEntity<ApiResponse<List<CatDto>>> catlist() {
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", catDtos));
	}
	
	//查詢所有可領養貓咪
	@GetMapping("/cat_alladoption")
	public ResponseEntity<ApiResponse<List<CatDto>>> allAdoption() {
		List <CatDto> catDtos = adoptionCatService.findAllAdoptionCats();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", catDtos));
	}
	
	//查看所有中途之家
	@GetMapping("/lomehome_list")
	public ResponseEntity<ApiResponse<List<LovehomeDto>>> lovehomeList(){
		List <LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", lovehomeDtos));
	}
	
	//查看個別貓的狀態
	@GetMapping("/cat/{catId}")
	public ResponseEntity<ApiResponse<CatDto>> getCat(@PathVariable Integer catId){
		CatDto catDto = adoptionCatService.getCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("查詢成功", catDto));
	}
	
}
