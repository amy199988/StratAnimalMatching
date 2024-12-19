package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.CallbackRequest;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.Impl.LineAuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LineCallbackController {

	@Autowired
	public LineAuthService lineAuthService;

	@PostMapping("/callback")
	public void callback(@RequestBody CallbackRequest callbackRequest, HttpServletResponse response) {
		try {
			String code = callbackRequest.getCode();
			String state = callbackRequest.getState();
			Integer userId = callbackRequest.getUserId();

			// 驗證 state
			if (state == null || userId == null) {
				throw new IllegalArgumentException("驗證失敗：state 或 userId 無效！");
			}
			
			// 根據 code 獲取 access token
			String accessToken = lineAuthService.getAccessToken(code);

			// 使用 access token 獲取用戶資料
			JSONObject userProfile = lineAuthService.getUserProfile(accessToken);
			
			UserDto userDto = lineAuthService.saveLineId(userProfile, userId);

			// 打印用戶資料（這裡您可以將用戶資料存儲到資料庫或進行其他處理）
			System.out.println("綁定用戶: " + userDto);
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			 // 發生錯誤時返回錯誤資訊
            System.err.println("LINE 綁定失敗: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
