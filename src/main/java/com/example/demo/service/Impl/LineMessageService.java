package com.example.demo.service.Impl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.constant.LineMessageTemplate;

@Service
public class LineMessageService {

	private static final String LINE_API_URL = "https://api.line.me/v2/bot/message/push";
	private static final String CHANNEL_ACCESS_TOKEN = "DUsUeYqVMS9KImmNg/JV3GTEUR6N7UE15y5VK9zGJJKS17TwjVUaxcxthF5LlPr1Y890wXflmG3VupetGYdhvoZCe1vKcrLd+2sfj+SoXXGty4leRQjzC/wX19hFOesTCEiDPLQL27I/3GG/xu/ixgdB04t89/1O/w1cDnyilFU=";

	public void sendMessage(String userId, String message) {
		try {
			// 1. 準備 HTTP 請求
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(CHANNEL_ACCESS_TOKEN);

			// 2. 建立訊息內容
			JSONObject body = new JSONObject();
			body.put("to", userId); // 接收者的 LINE User ID
			body.put("messages", List.of(new JSONObject().put("type", "text").put("text", message)));

			HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

			// 3. 發送 POST 請求
			restTemplate.postForEntity(LINE_API_URL, entity, String.class);
		} catch (Exception e) {
			throw new RuntimeException("LINE 訊息發送失敗: " + e.getMessage());
		}
	}
	
	public String getReportSuccessMessage(String city, String district, String address, String description) {
        return LineMessageTemplate.REPORT_SUCCES.getMessage(city, district, address, description);
    }
	
	public String getReportUpdateMessgae(Integer reportNumber, String reportStatus, String lovehomeName, String lovehomeCity, String lovehomeDistrict, String lovehomeAddress) {
		return LineMessageTemplate.REPORT_UPDATE.getMessage(reportNumber, reportStatus, lovehomeName, lovehomeCity, lovehomeDistrict, lovehomeAddress);
	}
	
	public String getAdoptionSuccessMessage(String catName) {
		return LineMessageTemplate.ADOPTION_SUCCES.getMessage(catName);
	}
	
	public String getAdoptionUpdateMessage(Integer requestNumber, String requestStatus, String catName) {
		return LineMessageTemplate.ADOPTION_UPDATE.getMessage(requestNumber,requestStatus,catName);
	}
	
	public String getReportRequestMessage(String reportCity, String reportDistrict, String reportAddress, String description) {
		return LineMessageTemplate.REPORT_REQUEST.getMessage(reportCity, reportDistrict, reportAddress, description);
	}
	
	public String getAdoptionRequestMessage(String catName, String userName) {
		return LineMessageTemplate.ADOPTION_REQUEST.getMessage(catName,userName);
	}
}
