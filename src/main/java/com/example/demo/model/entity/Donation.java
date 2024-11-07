package com.example.demo.model.entity;

import java.util.Date;
import lombok.Data;

//代表 donations 資料表的紀錄

@Data
public class Donation {
	private Integer donationsId; // 捐贈清單編號
	private Integer donorId; // 捐贈者編號
	private String donationType; // 捐贈類型
	private double amount; // 數量
	private String goodsDescription; // 捐贈描述
	private Date donationDate; // 捐贈日期
}
