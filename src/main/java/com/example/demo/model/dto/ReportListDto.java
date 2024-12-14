package com.example.demo.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportListDto {
	private Integer reportNumber; // 通報表單編號
	private UserDto userDto; // 通報人編號
	private Integer lovehomeId; // 選擇通報中途之家編號
	private String reportCity; // 通報地點城市
	private String reportDistrict; // 通報地點區域
	private String reportAddress; // 通報地點詳細地址
	private String photoBase64; // 通報相片網址
	private String description; // 通報描述
	private Timestamp reportDate; // 通報日期
	private String reportStatus; // 通報狀態(待辦、進行中、完成)
}
