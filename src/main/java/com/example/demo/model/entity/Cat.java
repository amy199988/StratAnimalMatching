package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 代表 cats 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private Integer catId; // 貓咪編號
	
	@Column(name = "cat_name", columnDefinition = "varchar(50)")
	private String catName; // 貓咪名稱
	
	@Column(name = "breed", columnDefinition = "varchar(50)")
	private String breed; // 貓咪花紋
	
	@Column(name = "age")
	private Integer age; // 貓咪年齡
	
	@Column(name = "health_status")
	private String healthStatus; // 貓咪身體狀況(特別疾病、受傷狀況)
	
	@Column(name = "description", columnDefinition = "text")
	private String description; // 詳細描述
	
	@Column(name = "catImage_Base64",  columnDefinition = "LONGTEXT")
	private String catImage_Base64; // 貓咪照片網址
	
	@Column(name = "is_apply", columnDefinition = "tinyint default 0")
	private Boolean isApply; // 是否可申請領養(false=0,true=1)
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "lovehome_id")
	private Lovehome lovehome; // 所在中途之家
	
	@OneToOne(mappedBy = "cat", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JsonBackReference
	private AdoptionRequest adoptionRequest; // 貓咪所屬申請領養單號
}
