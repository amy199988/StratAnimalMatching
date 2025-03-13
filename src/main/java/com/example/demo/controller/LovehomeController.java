package com.example.demo.controller;

import com.example.demo.aop.CheckRole;
import com.example.demo.constant.LineMessageTemplate;
import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.AdoptionRequestService;
import com.example.demo.service.Impl.LineMessageService;
import com.example.demo.service.LovehomeService;
import com.example.demo.service.ReportService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @Autowired
  private LineMessageService lineMessageService;

  // 查看愛媽資料與中途資訊
  @GetMapping
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<UserDto>> lovehomeSetting(HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    UserDto userDto = userService.getUserById(userCert.getUserId());
    lovehomeService.updateCurrentOccupancy(userCert.getLovehomeDto().getLovehomeId());
    return ResponseEntity.ok(ApiResponse.success("單筆查詢成功", userDto));
  }

  // 修改密碼
  @PostMapping("/password")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<String>> updatePassword(
      @RequestParam("oldPassword") String oldPassword,
      @RequestParam("newPassword") String newPassword, HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    Integer userId = userCert.getUserId();
    userService.updatePassword(userId, oldPassword, newPassword);
    return ResponseEntity.ok(ApiResponse.success("更新成功", null));
  }

  //更新會員資料(愛媽)
  @PutMapping("/user_update")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto upUserDto,
      HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    UserDto userDto = userService.getUserById(userCert.getUserId());
    userDto = userService.updateUser(upUserDto);
    return ResponseEntity.ok(ApiResponse.success("更新成功", userDto));
  }

  // 更新中途資料
  @PutMapping("/update")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<LovehomeDto>> updateLovehome(HttpSession session,
      @RequestBody LovehomeDto upLovehomeDto) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    LovehomeDto lovehomeDto = userCert.getLovehomeDto();
    lovehomeDto = lovehomeService.updateLovehome(upLovehomeDto);
    return ResponseEntity.ok(ApiResponse.success("更新成功", lovehomeDto));
  }

  // 新增貓咪
  @PostMapping("/cat_list")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<CatDto>> appendCat(@RequestBody CatDto catDto,
      HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    CatDto addCatDto = adoptionCatService.addCat(catDto, userCert.getLovehomeDto().getLovehomeId());
    lovehomeService.updateCurrentOccupancy(userCert.getLovehomeDto().getLovehomeId());
    return ResponseEntity.ok(ApiResponse.success("新增成功", addCatDto));
  }

  // 查看中途所擁有貓咪
  @GetMapping("/cat_list")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<List<CatDto>>> getLovehomecatList(HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    LovehomeDto lovehomeDto = userCert.getLovehomeDto();
    List<CatDto> catDtos = lovehomeService.getLovehomecatList(lovehomeDto.getLovehomeId());
    return ResponseEntity.ok(ApiResponse.success("獲取所有貓咪", catDtos));
  }

  // 查詢個別貓咪
  @GetMapping("/cat_list/{catId}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<CatDto>> getCatById(@PathVariable Integer catId) {
    CatDto catDto = adoptionCatService.getCatById(catId);
    return ResponseEntity.ok(ApiResponse.success("查詢成功", catDto));
  }

  // 更新貓咪
  @PutMapping("/cat_list/{catId}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<CatDto>> updateCat(@PathVariable Integer catId,
      @RequestBody CatDto catDto) {
    catDto = adoptionCatService.updateCat(catDto);
    return ResponseEntity.ok(ApiResponse.success("更新成功", catDto));
  }

  // 刪除貓咪
  @DeleteMapping("/cat_list/{catId}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<Void>> deleteCat(@PathVariable Integer catId) {
    adoptionCatService.deleteCatById(catId);
    return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
  }

  // 查看收到的通報列表
  @GetMapping("/report")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<List<ReportListDto>>> getLovehomeReportList(
      HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    LovehomeDto lovehomeDto = userCert.getLovehomeDto();
    List<ReportListDto> reportListDtos = reportService.getReportByLovehomeId(
        lovehomeDto.getLovehomeId());
    return ResponseEntity.ok(ApiResponse.success("獲取通報救援列表", reportListDtos));
  }

  // 查看個別通報資料
  @GetMapping("/report/{reportNumber}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<ReportListDto>> getReport(@PathVariable Integer reportNumber) {
    ReportListDto reportDto = reportService.getReportByNumber(reportNumber);
    return ResponseEntity.ok(ApiResponse.success("單筆查詢成功", reportDto));
  }

  // 修改個別通報資料送出
  @PutMapping("/report/{reportNumber}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<ReportListDto>> updateReport(@PathVariable Integer reportNumber,
      @RequestBody ReportListDto reportDto) {
    ReportListDto updateReportDto = reportService.updateReport(reportDto);
    if (reportDto.getUserDto().getLINEId() == null) {
      return ResponseEntity.ok(ApiResponse.success("修改成功", updateReportDto));
    }

    LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(reportDto.getLovehomeId());
    String statusMessage = LineMessageTemplate.getReportStatusMessage(reportDto.getReportStatus());
    String lineMessage = lineMessageService.getReportUpdateMessgae(reportNumber, statusMessage,
        lovehomeDto.getLovehomeName(), lovehomeDto.getLovehomeCity(),
        lovehomeDto.getLovehomeDistrict(),
        lovehomeDto.getLovehomeAddress());
    lineMessageService.sendMessage(reportDto.getUserDto().getLINEId(), lineMessage);
    return ResponseEntity.ok(ApiResponse.success("修改成功", updateReportDto));
  }

  // 刪除個別通報清單
  @DeleteMapping("/report/{reportNumber}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<AdoptionRequestDto>> deleteReport(
      @PathVariable Integer reportNumber) {
    reportService.deleteReport(reportNumber);
    return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
  }

  // 查看申請領養的表單
  @GetMapping("/request")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<List<AdoptionRequestDto>>> getAdoptionRequestList(
      HttpSession session) {
    UserCert userCert = (UserCert) session.getAttribute("userCert");
    LovehomeDto lovehomeDto = userCert.getLovehomeDto();
    List<AdoptionRequestDto> adoptionRequestDtos = adoptionRequestService
        .getRequestsBylovehomeId(lovehomeDto.getLovehomeId());
    return ResponseEntity.ok(ApiResponse.success("獲取申請領養的清單", adoptionRequestDtos));
  }

  // 查看個別領養清單
  @GetMapping("/request/{requestNumber}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<AdoptionRequestDto>> getRequest(
      @PathVariable Integer requestNumber) {
    AdoptionRequestDto adoptionRequestDto = adoptionRequestService.getRequestByNumber(
        requestNumber);
    return ResponseEntity.ok(ApiResponse.success("單筆查詢成功", adoptionRequestDto));
  }

  // 修改個別領養清單送出
  @PutMapping("/request/{requestNumber}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<AdoptionRequestDto>> updateRequest(
      @PathVariable Integer requestNumber,
      @RequestBody AdoptionRequestDto adoptionRequestDto) {
    AdoptionRequestDto upadoptionRequestDto = adoptionRequestService.updateAdoptionRequest(
        adoptionRequestDto);

    if (adoptionRequestDto.getUserDto().getLINEId() == null) {
      return ResponseEntity.ok(ApiResponse.success("修改成功", upadoptionRequestDto));
    }

    String statusMessage = LineMessageTemplate.getAdoptionStatusMessage(
        adoptionRequestDto.getRequestStatus());
    String lineMessage = lineMessageService.getAdoptionUpdateMessage(requestNumber, statusMessage,
        adoptionRequestDto.getCatDto().getCatName());
    lineMessageService.sendMessage(adoptionRequestDto.getUserDto().getLINEId(), lineMessage);
    return ResponseEntity.ok(ApiResponse.success("修改成功", upadoptionRequestDto));
  }

  // 刪除個別領養清單
  @DeleteMapping("/request/{requestNumber}")
  @CheckRole({"role_lovemom", "role_manager"})
  public ResponseEntity<ApiResponse<AdoptionRequestDto>> deleteRequest(
      @PathVariable Integer requestNumber) {
    adoptionRequestService.deleteRequestByNumber(requestNumber);
    return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
  }
}
