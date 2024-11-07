package com.example.demo.model.entity;

import java.util.Date;
import lombok.Data;

// 代表 adoption_requests 資料表的紀錄

@Data
public class adoptionRequest {
	private Integer requestNumber; // 領養表單編號
	private Integer applicantId; // 申請領養者編號
	private Integer adoptedcatId; // 被領養貓咪編號
	private Date requstDate; // 申請日期
	private String requestStatus; // 申請狀態(待辦、通過、不通過)
}
