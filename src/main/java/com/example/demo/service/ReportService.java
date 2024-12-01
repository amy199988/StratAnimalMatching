package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ReportDto;

public interface ReportService {
	//新增
	public ReportDto addReport(ReportDto reportDto);
	//查詢全部
	public List<ReportDto> getAllReport();
	//查詢單個
	public ReportDto getReportByNumber(Integer reportNumber);
	//更新
	public ReportDto updateReport(ReportDto reportDto);
	
	//刪除
	public void deleteReport(Integer reportNumber);
	
}
