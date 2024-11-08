package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LovehomeDto {
	private Integer lovehomeId; // 中途之家編號
	private String lovehomename; // 中途之家名稱
	private String lovehomeAddress; // 中途之家地址
	private String contactInfo; // 中途之家聯絡方式
	private Integer capacity; // 中途之家可收容容量
	private Integer currentOccupancy; // 中途之家目前佔用率
}
