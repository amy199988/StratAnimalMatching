package com.example.demo.controller;

import com.example.demo.model.dto.UserCert;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.CertService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * WEB API
 * --------------------------
 * Servlet-Patf: /auth
 * --------------------------
 * POST  /auth/login      登入
 * GET   /auth/logout     登出
 * POST  /auth/sign_up    註冊
 *
 *
 * POST /forget_password 忘記密碼
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

  @Autowired
  private CertService certService;

  @Autowired
  private UserService userService;

  // 登入
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<UserDto>> login(@RequestParam("account") String account,
      @RequestParam("password") String password, HttpSession session) {
    // 取得憑證
    UserCert userCert = certService.getCert(account, password);
    // 放入憑證
    session.setAttribute("userCert", userCert);
    UserDto userDto = userService.getUserById(userCert.getUserId());
    return ResponseEntity.ok(ApiResponse.success("登入成功", userDto));
  }

  // 登出
  @GetMapping("/logout")
  public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
    session.invalidate(); // session 失效
    return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));

  }

  // 註冊
  @PostMapping("/sign_up")
  public ResponseEntity<ApiResponse<String>> addUser(@RequestBody UserDto userDto) {
    userService.addUser(userDto);
    userService.sendVerificationEmail(userDto.getAccount(), userDto.getEmail());
    return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
  }

  @GetMapping("/check")
  public ResponseEntity<ApiResponse<Boolean>> checkSession(HttpSession session) {
    if (session != null && session.getAttribute("userCert") != null) {
      // Session 有效
      return ResponseEntity.ok(ApiResponse.success("Session有效", true));
    } else {
      // Session 無效
      throw new RuntimeException("Session is invalid or expired");
    }
  }

  // 忘記密碼：發送重設密碼信件
  @PostMapping("/forget_password")
  public ResponseEntity<ApiResponse<String>> sendResetPasswordEmail(
      @RequestParam("account") String account,
      @RequestParam("email") String email) {
    userService.sendResetPasswordEmail(account, email);
    return ResponseEntity.ok(ApiResponse.success("發送成功", null));
  }

  // 忘記密碼：處理重設密碼請求
  @PostMapping("/reset_password")
  public ResponseEntity<ApiResponse<String>> resetPassword(@RequestParam("token") String token,
      @RequestParam String newPassword) {
    userService.resetPassword(token, newPassword);
    return ResponseEntity.ok(ApiResponse.success("修改密碼成功", null));
  }

//	// 帳號驗證寄信
//  @PostMapping("/send_verification")
//  public ResponseEntity<ApiResponse<String>> sendVerificationEmail(@RequestParam Integer userId, @RequestParam String email) {
//     userService.sendVerificationEmail(userId, email);
//     return ResponseEntity.ok(ApiResponse.success("發送成功",null));
//  }

  // 帳戶驗證：處理驗證連結
  @GetMapping("/verify")
  public ResponseEntity<ApiResponse<String>> passEmail(@RequestParam("account") String account) {
    userService.passEmail(account);
    return ResponseEntity.ok(ApiResponse.success("帳號驗證成功", null));
  }

}
