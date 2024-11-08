package com.example.demo.model.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportListDto {
	private Integer reportnumber; // 通報表單編號
	private Integer reportedId; // 通報人編號
	private String location; // 通報地點
	private String photourl; // 通報相片網址
	private String description; // 通報描述
	private Timestamp reportDate; // 通報日期
	private String reportstatus; // 通報狀態(待辦、進行中、完成)
	private Integer assignedId; // 分配中途之家編號
}
