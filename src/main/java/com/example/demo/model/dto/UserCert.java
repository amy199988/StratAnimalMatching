package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCert {

  private Integer userId; // 使用者ID
  private String role; // 角色權限
  private LovehomeDto lovehomeDto; // 中途之家ID
}
