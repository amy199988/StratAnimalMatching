package com.example.demo.service.Impl;

import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import java.util.Collections;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class LineAuthService {

  private static final String CLIENT_ID = "2006672068";
  private static final String CLIENT_SECRET = "68230eb0f1ec528620217838b07dd670";
  private static final String REDIRECT_URI = "https://db38-2402-7500-486-9058-bd8a-28ec-1628-e0ea.ngrok-free.app/callback";
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private Mapper mapper;

  // 根據 authorization code 交換 access_token
  public String getAccessToken(String code) {
    String tokenUrl = "https://api.line.me/oauth2/v2.1/token";
    // 構建請求的表單數據
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("grant_type", "authorization_code");
    formData.add("code", code);
    formData.add("redirect_uri", REDIRECT_URI);
    formData.add("client_id", CLIENT_ID);
    formData.add("client_secret", CLIENT_SECRET);

    // 準備 HTTP 請求標頭
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    // 準備請求的表單數據
    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

    RestTemplate restTemplate = new RestTemplate();

    try {
      // 發送 POST 請求
      ResponseEntity<String> responseEntity = restTemplate.exchange(tokenUrl, HttpMethod.POST,
          requestEntity, String.class);
      String responseStr = responseEntity.getBody();

      // 解析 JSON 取得 access_token
      JSONObject responseJson = new JSONObject(responseStr);
      return responseJson.getString("access_token"); // 從 JSON 回應中提取 access_token
    } catch (HttpClientErrorException e) {
      throw new RuntimeException("Error during LINE callback: " + e.getMessage());
    }
  }

  // 使用 access_token 獲取用戶的 LINE 資料
  public JSONObject getUserProfile(String accessToken) {
    String profileUrl = "https://api.line.me/v2/profile";
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + accessToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(profileUrl, HttpMethod.GET, entity,
        String.class);

    return new JSONObject(response.getBody()); // 返回用戶資料的 JSON
  }

  // 獲取用戶的 LINE 資料中的 LineId
  public UserDto saveLineId(JSONObject userProfile, Integer userId) {
    String lineUserId = userProfile.getString("userId");
    User user = userRepository.findById(userId).get();
    user.setLINEId(lineUserId);
    userRepository.save(user);
    return mapper.toUserDto(user);
  }
}
