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
import com.example.demo.model.dto.ReportDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.LovehomeService;
import com.example.demo.service.ReportService;


/*
 * WEB API
 * --------------------------
 * 中途之家使用
 * Servlet-Patf: /lovehome
 * --------------------------
 * GET   /lovehome                        中途服務頁面
 * GET   /lovehome/setting/{lovehomeId}   查看中途資料
 * GET   /lovehome/update/{lovehomeId}    更新中途資料頁面
 * POST  /lovehome/update/{lovehomeId}    更新中途資料
 * GET   /lovehome/cat_add                貓咪新增頁面
 * POST  /lovehome/cat_add                貓咪新增資料送出
 * GET   /lovehome/cat_list/{lovehomeId}  查看所有上傳貓咪
 * GET   /lovehome/cat_update/{catId}     貓咪資訊修改頁面
 * PUT   /lovehome/cat_update/{catId}     貓咪資訊修改送出
 * DELETE /lovehome/cat_delete/{catId}    刪除貓咪
 * GET    /lovehome/report/{lovehomeId}   查看收到的通報列表
 * GET    /lovehome/report/{reportNumber} 查看個別通報資料
 * GET    /lovehome/report_update/{reportNumber} 修改個別通報資料
 * PUT    /lovehome/report_update/{reportNumber} 修改個別通報資料送出
 * 
 * 
 */

//@RestControlle
@Controller
@RequestMapping("/lovehome")
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class LovehomeController {

	@Autowired
	private LovehomeService lovehomeService;

	@Autowired
	private AdoptionCatService adoptionCatService;
	
	@Autowired
	private ReportService reportService;
	
/*
	@GetMapping("/lovemom")
	public String lovemom() {
		return "/User/lovemom";
	}
	
	@GetMapping("/lovehome/add")
	public String lovehomeadd() {
		return "lovehome_add";
	}

	@PostMapping("/lovehome/add")
	public String appendLovehome(Lovehome lovehome, @RequestParam("photo") MultipartFile photoFile, Model model) {
		if (model == null) {
			System.out.println("Model is null");
		}
		lovehome = lovehomeService.addLovehome(lovehome, photoFile);
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		model.addAttribute("lovehomeDtos", lovehomeDtos);
		return "/Lovehome/lovehome";
	}

	//愛媽資訊
	@GetMapping("/lovehome/setting")
	public String lovehomeSetting(@RequestParam Integer lovehomeId, Model model) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		model.addAttribute("lovehomeDto", lovehomeDto);
		return "/Lovehome/lovehome_setting";
	}
	
	//更新愛媽資訊
	@GetMapping("/lovehome/update")
	public String updateLovehome(@RequestParam Integer lovehomeId, Model model) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		model.addAttribute("lovehomeDto", lovehomeDto);
		return "/Lovehome/lovehome_update";
	}

	@PostMapping("/lovehome/update")
	public String updateLovehome(@RequestParam Integer lovehomeId, Lovehome lovehome,
			@RequestParam("photo") MultipartFile photoFile, Model model) {
		if (photoFile == null || photoFile.isEmpty()) {
			lovehome = lovehomeService.updateLovehomeWithoutPhoto(lovehome);
			List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
			model.addAttribute("lovehomeDtos", lovehomeDtos);
			return "/Lovehome/lovehome_setting";
		}
		lovehome = lovehomeService.updateLovehome(lovehome, photoFile);
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		model.addAttribute("lovehomeDtos", lovehomeDtos);
		return "/Lovehome/lovehome_setting";
	}
	
	//新增貓咪
	@GetMapping("/cat/add")
	public String catadd() {
		return "/AdoptionCat/cat_add";
	}

	@PostMapping("/cat/add")
	public String appendCat(Cat cat, @RequestParam("photo") MultipartFile photoFile, Model model) {
		if (model == null) {
			System.out.println("Model is null");
		}
		cat = adoptionCatService.addCat(cat, photoFile);
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		model.addAttribute("catDtos", catDtos);
		return "/AdoptionCat/cat_list";
	}
	
	//更新貓咪
	@GetMapping("/cat/update")
	public String updateCat(@RequestParam("catId") Integer catId, Model model) {
		CatDto catDto = adoptionCatService.getCatById(catId);
		model.addAttribute("catDto", catDto);
		return "cat_update";
	}

	@PostMapping("/cat/update")
	public String updateCat(@RequestParam("catId") Integer catId, Cat cat,
			@RequestParam("photo") MultipartFile photoFile, Model model) {
		if (photoFile == null || photoFile.isEmpty()) {
			cat = adoptionCatService.updateCatWithoutPhoto(cat);
			List<CatDto> catDtos = adoptionCatService.findAllCats();
			model.addAttribute("catDtos", catDtos);
			return "/AdoptionCat/cat_list";
		}
		cat = adoptionCatService.updateCat(cat, photoFile);
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		model.addAttribute("catDtos", catDtos);
		return "/AdoptionCat/cat_list";
	}

	//刪除
	@DeleteMapping("/cat/delete")
	public String deleteCat(@RequestParam("catId") Integer catId, Model model) {
		adoptionCatService.deleteCatById(catId);
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		model.addAttribute("catDtos", catDtos);
		return "/AdoptionCat/cat_list";
	}
*/

	//查看中途資訊
	@GetMapping("/setting/{lovehomeId}")
	public ResponseEntity<ApiResponse<LovehomeDto>> lovehomeSetting(@PathVariable Integer lovehomeId) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",lovehomeDto));
	}
	
	//更新中途資料
	@GetMapping("/update/{lovehomeId}")
	public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(@PathVariable Integer lovehomeId) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",lovehomeDto));
	}
	
	@PostMapping("/update/{lovehomeId}")
	public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(@PathVariable Integer lovehomeId,@RequestBody LovehomeDto lovehomeDto,
			@RequestParam("photo") MultipartFile photoFile) {
		if (photoFile == null || photoFile.isEmpty()) {
			lovehomeDto = lovehomeService.updateLovehomeWithoutPhoto(lovehomeDto);
			return ResponseEntity.ok(ApiResponse.success("更新成功",lovehomeDto));
		}
		lovehomeDto = lovehomeService.updateLovehome(lovehomeDto, photoFile);
		return ResponseEntity.ok(ApiResponse.success("更新成功",lovehomeDto));
	}
	
	//新增貓咪
	@PostMapping("/cat_add")
	public ResponseEntity<ApiResponse<CatDto>> appendCat(@RequestBody CatDto catDto, @RequestParam("photo") MultipartFile photoFile) {
		CatDto addCatDto = adoptionCatService.addCat(catDto, photoFile);
		return ResponseEntity.ok(ApiResponse.success("新增成功",addCatDto));
	}
	
	//查看中途所上傳貓咪
	@GetMapping("/cat_list/{lovehomeId}")
	public ResponseEntity<ApiResponse<List<CatDto>>> getLovehomecatList(@PathVariable Integer lovehomeId) {
		List<CatDto> cats = lovehomeService.getLovehomecatList(lovehomeId);
		return ResponseEntity.ok(ApiResponse.success("獲取所上傳的貓",cats));
	}
	
	//更新貓咪
	@GetMapping("/cat_update/{catId}")
	public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId) {
		CatDto catDto = adoptionCatService.getCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",catDto));
	}
	
	@PostMapping("/cat_update/{catId}")
	public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId, CatDto catDto,
			@RequestParam("photo") MultipartFile photoFile) {
		if (photoFile == null || photoFile.isEmpty()) {
			catDto = adoptionCatService.updateCatWithoutPhoto(catDto);
			return ResponseEntity.ok(ApiResponse.success("更新成功",catDto));
		}
		catDto = adoptionCatService.updateCat(catDto, photoFile);
		return ResponseEntity.ok(ApiResponse.success("更新成功",catDto));
	}
	
	//刪除貓咪
	@DeleteMapping("/cat_delete/{catId}")
	public ResponseEntity<ApiResponse<Void>> deleteCat(@PathVariable Integer catId) {
		adoptionCatService.deleteCatById(catId);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}
	
	//查看收到的通報列表
	@GetMapping("/report/{lovehomeId}")
	public ResponseEntity<ApiResponse<List<ReportDto>>> getLovehomeReportList(@PathVariable Integer lovehomeId){
		List<ReportDto> reportListDtos = lovehomeService.getLovehomeReportList(lovehomeId);
		return ResponseEntity.ok(ApiResponse.success("獲取所上傳的貓",reportListDtos));
	}
	
	//查看個別通報資料
	@GetMapping("/report/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportDto>> getReport(@PathVariable Integer reportNumber) {
		ReportDto reportDto = reportService.getReportByNumber(reportNumber);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",reportDto));
	}
	
	//修改個別通報資料
	@GetMapping("/report_update/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportDto>> updateReport(@PathVariable Integer reportNumber) {
		ReportDto reportDto = reportService.getReportByNumber(reportNumber);
		return ResponseEntity.ok(ApiResponse.success("單筆查詢成功",reportDto));
	}
	
	//修改個別通報資料送出
	@PutMapping("/report_update/{reportNumber}")
	public ResponseEntity<ApiResponse<ReportDto>> updateReport(@RequestBody ReportDto reportDto) {
		ReportDto updateReportDto = reportService.updateReport(reportDto);
		return ResponseEntity.ok(ApiResponse.success("修改成功",updateReportDto));
	}
	
	
	
}
