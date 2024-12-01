package com.example.demo.model.dto;

import java.sql.Timestamp;

import com.example.demo.model.entity.Lovehome;
import com.example.demo.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
	private Integer reportNumber; // 通報表單編號
	private User user; // 通報人編號
	private String reportCity; // 通報地點城市
	private String reportDistrict; // 通報地點區域
	private String reportAddress; // 通報地點詳細地址
	private String photoUrl; // 通報相片網址
	private String description; // 通報描述
	private Timestamp reportDate; // 通報日期
	private String reportStatus; // 通報狀態(待辦、進行中、完成)
	private Lovehome lovehome; // 分配中途之家編號
}
