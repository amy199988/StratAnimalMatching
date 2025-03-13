package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationInventoryDto {

  private Integer inventoryId; // 物資編號
  private String inventoryName; // 物資名稱
  private String description; // 物資描述
  private Integer current; // 物資庫存數量
}
