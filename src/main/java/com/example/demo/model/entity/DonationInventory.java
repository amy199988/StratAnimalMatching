package com.example.demo.model.entity;

import lombok.Data;

// 代表 donation_inventory 資料表的紀錄

@Data
public class DonationInventory {
	private Integer inventoryId; // 物資編號
	private String inventoryname; // 物資名稱
	private String description; // 物資描述
	private Integer current; // 物資庫存數量
}
