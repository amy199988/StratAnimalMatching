package com.example.demo.model.entity;

import java.sql.Timestamp;
import lombok.Data;

// 代表 notices 資料表的紀錄

@Data
public class Notice {
	private Integer noticeId; // 通知編號
	private String recipientemail; // 收件人信箱
	private String subject; // 主題
	private String message; // 信息
	private Timestamp sendDate; // 發送日期
}
