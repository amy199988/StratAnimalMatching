package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 lovehomes 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lovehomes")
public class Lovehome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lovehome_id")
	private Integer lovehomeId; // 中途之家編號
	
	@Column(name = "lovehome_name", columnDefinition = "varchar(50)")
	private String lovehomeName; // 中途之家名稱
	
	@Column(name = "lovehome_city")
	private String lovehomeCity; // 中途之家城市
	
	@Column(name = "lovehome_district")
	private String lovehomeDistrict; //中途之家區域
	
	@Column(name = "lovehome_address")
	private String lovehomeAddress; // 中途之家詳細地址
	
	@Column(name = "contact_info")
	private String contactInfo; // 中途之家聯絡方式
	
	@Column(name = "capacity")
	private Integer capacity; // 中途之家可收容容量
	
	@Column(name = "current_occupancy")
	private Integer currentOccupancy; // 中途之家目前佔用率
	
	@OneToMany(mappedBy = "lovehomes", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Cat> cats; // 中途之家所收容貓咪
	
	@OneToMany(mappedBy = "lovehomes", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<ReportList> reportLists; // 中途之家收到的通報清單
	
}
