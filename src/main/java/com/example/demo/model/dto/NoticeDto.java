package com.example.demo.model.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
	private Integer noticeId; // 通知編號
	private String recipientemail; // 收件人信箱
	private String subject; // 主題
	private String message; // 信息
	private Timestamp sendDate; // 發送日期
}
