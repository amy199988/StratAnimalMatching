package com.example.demo.model.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 notices 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

  private Integer noticeId; // 通知編號
  private String recipientemail; // 收件人信箱
  private String subject; // 主題
  private String message; // 信息
  private Timestamp sendDate; // 發送日期
}
