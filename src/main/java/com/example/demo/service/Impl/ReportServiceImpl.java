package com.example.demo.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.ReportDto;
import com.example.demo.model.entity.ReportList;
import com.example.demo.repository.ReportRepository;
import com.example.demo.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private Mapper mapper;
	
	
	@Override
	public ReportDto addReport(ReportDto reportDto) {
		ReportList reportList = mapper.toReportListEntity(reportDto);
		reportRepository.save(reportList);
		return mapper.toReportListDto(reportList);
	}

	@Override
	public List<ReportDto> getAllReport() {
		return reportRepository.findAll().stream().map(mapper::toReportListDto).collect(Collectors.toList());
	}

	@Override
	public ReportDto getReportByNumber(Integer reportNumber) {
		ReportList report = reportRepository.findById(reportNumber)
				.orElseThrow(() -> new UserNotFoundException("無此通報單：" + reportNumber));
		return mapper.toReportListDto(report);
	}

	@Override
	public ReportDto updateReport(ReportDto reportDto) {
		return reportRepository.findById(reportDto.getReportNumber())
				.map(report ->{
					mapper.toReportListEntity(reportDto);
					ReportList upReportList = reportRepository.save(report);
					return mapper.toReportListDto(upReportList);
				})
				.orElseThrow(() -> new RuntimeException("查無資料"));
	}

	@Override
	public void deleteReport(Integer reportNumber) {
		reportRepository.deleteById(reportNumber);
		
	}

}
