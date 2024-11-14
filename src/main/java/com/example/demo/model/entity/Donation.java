package com.example.demo.model.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//代表 donations 資料表的紀錄

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donations_id")
	private Integer donationsId; // 捐贈清單編號
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "donor_id")
	private User user; // 捐贈者編號
	
	@Column(name = "donation_type", columnDefinition = "enum('money', 'goods')")
	private String donationType; // 捐贈類型
	
	@Column(name = "amount", columnDefinition = "decimal(10,2)")
	private double amount; // 數量
	
	@Column(name = "goods_description", columnDefinition = "text")
	private String goodsDescription; // 捐贈描述
	
	@Column(name = "donation_date", columnDefinition = "date default (CURRENT_DATE)")
	private Date donationDate; // 捐贈日期
}
