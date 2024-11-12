package com.example.demo.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 adoption_requests 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_number")
	private Integer requestNumber; // 領養表單編號
	
	@Column(name = "applicant_id")
	private Integer applicantId; // 申請領養者編號
	
	@Column(name = "adoptedcat_id")
	private Integer adoptedcatId; // 被領養貓咪編號
	
	@Column(name = "request_date", columnDefinition = "date default (CURRENT_DATE)")
	private Date requstDate; // 申請日期
	
	@Column(name = "request_status", columnDefinition = "enum default 'pending'")
	private String requestStatus; // 申請狀態(待辦、通過、不通過)
}
