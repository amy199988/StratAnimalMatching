package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

  private Integer userId; // 會員編號
  private String userName; // 會員名稱
  private String account; // 會員帳號
  private String password; // 會員密碼
  private String phone; // 電話
  private Date birthdate; // 生日
  private String email; // 電子郵件
  private Boolean active; // 帳號認證
  private String role; // 帳號權限(普通、愛媽、管理員)
  private String LINEId; // 會員所關聯LINEID
  private String resetToken; // 密碼重設 Token
  private LovehomeDto lovehomeDto; // 會員所擁有的中途之家(可為null)
}
