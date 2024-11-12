package com.example.demo.util;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Imgur {
	
	@Value("${imgur.client-id}")
	private String clientId;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public String uploadImage(MultipartFile file) {
		try {
			// 圖片轉換成 base64 字符串
			byte[] imageBytes = file.getBytes();
			String encodeImage = Base64.getEncoder().encodeToString(imageBytes);
			
			// 設定 Imgur API 的 header 和請求體
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Client-id" + clientId);
			
			Map<String, String> body = new HashMap<>();
			body.put("image", encodeImage);
			
			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body,headers);
			
			// 發送 post 請求上傳圖片
			String url = "https://api.imgur.com/3/image";
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

            // 獲取圖片的連結
            Map<String, Object> responseData = (Map<String, Object>) response.getBody().get("data");
            return (String) responseData.get("link");
            
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
	}
}
