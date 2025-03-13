package com.example.demo.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 reported_lists 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reported_lists")
public class ReportList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reported_number")
  private Integer reportNumber; // 通報表單編號

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
      CascadeType.DETACH})
  @JoinColumn(name = "reportedId")
  private User user; // 通報人編號

  @Column(name = "report_city")
  private String reportCity; // 通報地點城市

  @Column(name = "report_district")
  private String reportDistrict; // 通報地點區域

  @Column(name = "report_address")
  private String reportAddress; // 通報地點詳細地址

  @Column(name = "photo_Base64", columnDefinition = "LONGTEXT")
  private String photoBase64; // 通報相片網址

  @Column(name = "description", columnDefinition = "text")
  private String description; // 通報描述

  @Column(name = "report_date", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
  private Timestamp reportDate; // 通報日期

  @Column(name = "report_status", columnDefinition = "enum('pending', 'in_progress', 'resolved') default 'pending'")
  private String reportStatus; // 通報狀態(待辦、進行中、完成)

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,
      CascadeType.DETACH})
  @JoinColumn(name = "assigned_id")
  private Lovehome lovehome; // 分配中途之家編號
}
