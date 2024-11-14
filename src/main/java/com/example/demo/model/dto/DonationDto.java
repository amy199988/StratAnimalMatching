package com.example.demo.model.dto;

import java.util.Date;

import com.example.demo.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationDto {
	private Integer donationsId; // 捐贈清單編號
	private User user; // 捐贈者編號
	private String donationType; // 捐贈類型
	private double amount; // 數量
	private String goodsDescription; // 捐贈描述
	private Date donationDate; // 捐贈日期
}
