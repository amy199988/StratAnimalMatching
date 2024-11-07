package com.example.demo.model.entity;

import lombok.Data;

// 代表 lovehomes 資料表的紀錄

@Data
public class Lovehome {
	private Integer lovehomeId; // 中途之家編號
	private String lovehomename; // 中途之家名稱
	private String lovehomeAddress; // 中途之家地址
	private String contactInfo; // 中途之家聯絡方式
	private Integer capacity; // 中途之家可收容容量
	private Integer currentOccupancy; // 中途之家目前佔用率
}
