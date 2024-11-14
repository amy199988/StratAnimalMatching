package com.example.demo.model.dto;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.entity.AdoptionRequest;
import com.example.demo.model.entity.Donation;
import com.example.demo.model.entity.ReportList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer userId; // 會員編號
	private String userName; // 會員名稱
	private String account; // 會員帳號
	private Integer phone; // 電話
	private Date birthdate; // 生日
	private String email; // 電子郵件
	private Boolean active; // 帳號認證
	private String role; // 帳號權限(普通、愛媽、管理員)
	private List<AdoptionRequest> adoptionRequests; // 會員所擁有的申請領養表單
	private List<Donation> donations; // 會員所擁有的捐贈表單
	private List<ReportList> reportLists; // 會員所擁有的通報救援表單
}
