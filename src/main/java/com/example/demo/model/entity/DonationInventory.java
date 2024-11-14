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

// 代表 donation_inventory 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donation_inventorys")
public class DonationInventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private Integer inventoryId; // 物資編號
	
	@Column(name = "inventory_name", columnDefinition = "varchar(50)")
	private String inventoryName; // 物資名稱
	
	@Column(name = "description", columnDefinition = "text")
	private String description; // 物資描述
	
	@Column(name = "current_quantity", columnDefinition = "int deafult 0")
	private Integer current; // 物資庫存數量
}
