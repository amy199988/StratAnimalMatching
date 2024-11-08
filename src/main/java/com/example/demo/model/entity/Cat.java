package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 cats 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
	private Integer catId; // 貓咪編號
	private String catname; // 貓咪名稱
	private String breed; // 貓咪花紋
	private Integer age; // 貓咪年齡
	private String healthStatus; // 貓咪身體狀況(特別疾病、受傷狀況)
	private String description; // 詳細描述
	private Boolean is_apply; // 是否可申請領養(false=0,true=1)
}
