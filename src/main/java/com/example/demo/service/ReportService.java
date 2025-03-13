package com.example.demo.service;

import com.example.demo.model.dto.ReportListDto;
import java.util.List;

public interface ReportService {

  //新增
  public ReportListDto addReport(ReportListDto reportDto);

  //查詢全部
  public List<ReportListDto> getAllReport();

  //查詢單個
  public ReportListDto getReportByNumber(Integer reportNumber);

  //更新
  public ReportListDto updateReport(ReportListDto reportDto);

  //刪除
  public void deleteReport(Integer reportNumber);

  //根據使用者ID查詢
  public List<ReportListDto> getReportByUserId(Integer userId);

  //根據中途之家ID查詢
  public List<ReportListDto> getReportByLovehomeId(Integer lovehomeId);
}
