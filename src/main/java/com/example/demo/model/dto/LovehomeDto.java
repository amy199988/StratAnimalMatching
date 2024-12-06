package com.example.demo.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LovehomeDto {
	private Integer lovehomeId; // 中途之家編號
	private String lovehomeName; // 中途之家名稱
	private String lovehomeCity; // 中途之家城市
	private String lovehomeDistrict; // 中途之家區域
	private String lovehomeAddress; // 中途之家地址
	private String contactInfo; // 中途之家聯絡方式
	private Double capacity; // 中途之家可收容容量
	private Double currentOccupancy; // 中途之家目前佔用率
	private String lovehomeImage_Base64; // 中途之家照片Base64
	private List<CatDto> catDtos; // 中途之家所收容貓咪
	private List<ReportListDto> reportListDtos; // 中途之家收到的通報清單
}
