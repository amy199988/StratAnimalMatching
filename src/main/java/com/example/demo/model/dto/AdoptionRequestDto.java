package com.example.demo.model.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequestDto {
	private Integer requestNumber; // 領養表單編號
	private Integer applicantId; // 申請領養者編號
	private Integer adoptedcatId; // 被領養貓咪編號
	private Date requstDate; // 申請日期
	private String requestStatus; // 申請狀態(待辦、通過、不通過)
}
