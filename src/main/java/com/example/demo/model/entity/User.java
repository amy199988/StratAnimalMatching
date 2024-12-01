package com.example.demo.model.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 users 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId; // 會員編號
	
	@Column(name = "user_name", columnDefinition = "varchar(50)")
	private String userName; // 會員名稱
	
	@Column(name = "account", columnDefinition = "varchar(50)", nullable = false, unique = true)
	private String account; // 會員帳號
	
	@Column(name = "password_hash", nullable = false, unique = true)
	private String passwordHash; // 會員Hash密碼
	
	@Column(name = "salt", nullable = false, unique = true)
	private String salt; // 隨機鹽
	
	@Column(name = "phone")
	private String phone; // 電話
	
	@Column(name = "birthdate")
	private Date birthdate; // 生日
	
	@Column(name = "email")
	private String email; // 電子郵件
	
	@Column(name = "active", columnDefinition = "tinyint default 0")
	private Boolean active; // 帳號認證
	
	@Column(name = "role")
	private String role; // 帳號權限(普通、愛媽、管理員)
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AdoptionRequest> adoptionRequests; // 會員所擁有的申請領養表單
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Donation> donations; // 會員所擁有的捐贈表單
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ReportList> reportLists; // 會員所擁有的通報救援表單
	
	@ManyToOne
	@JoinColumn(name = "lovehome_id")
	private Lovehome lovehome; // 會員所擁有的中途之家權限(普通會員為null)
}
