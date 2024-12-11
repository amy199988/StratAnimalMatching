package com.example.demo.model.dto;

import java.sql.Date;

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
	private String password; // 會員密碼
	private String phone; // 電話
	private Date birthdate; // 生日
	private String email; // 電子郵件
	private Boolean active; // 帳號認證
	private String role; // 帳號權限(普通、愛媽、管理員)
	//private List<AdoptionRequestDto> adoptionRequestsdDtos; // 會員所擁有的申請領養表單
	//private List<DonationDto> donationsdDtos; // 會員所擁有的捐贈表單
	//private List<ReportListDto> reportListsdDtos; // 會員所擁有的通報救援表單
	private LovehomeDto lovehomeDto; // 會員所擁有的中途之家(可為null)
}
