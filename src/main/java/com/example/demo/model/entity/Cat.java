package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 cats 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private Integer catId; // 貓咪編號
	
	@Column(name = "cat_name")
	private String catname; // 貓咪名稱
	
	@Column(name = "breed")
	private String breed; // 貓咪花紋
	
	@Column(name = "age")
	private Integer age; // 貓咪年齡
	
	@Column(name = "health_status")
	private String healthStatus; // 貓咪身體狀況(特別疾病、受傷狀況)
	
	@Column(name = "description")
	private String description; // 詳細描述
	
	@Column(name = "catphoto_url")
	private String catphoto_url; // 貓咪照片上傳網址
	
	@Column(name = "is_apply")
	private Boolean is_apply; // 是否可申請領養(false=0,true=1)
}
