package com.example.demo.model.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "applicant_id")
	private User user; // 申請領養者編號
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "adoptedcat_id")
	private Cat cat; // 被領養貓咪編號
	
	@Column(name = "request_date", columnDefinition = "date default (CURRENT_DATE)")
	private Date requstDate; // 申請日期
	
	@Column(name = "request_status", columnDefinition = "enum('pending', 'approved', 'rejected') default 'pending'")
	private String requestStatus; // 申請狀態(待辦、通過、不通過)
	
}
