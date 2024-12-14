package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ReportNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.entity.ReportList;
import com.example.demo.repository.LovehomeRepository;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LovehomeRepository lovehomeRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public ReportListDto addReport(ReportListDto reportDto) {
		ReportList reportList = mapper.toReportListEntity(reportDto);
		reportList.setUser(userRepository.findById(reportDto.getUserDto().getUserId()).get());
		reportList.setLovehome(lovehomeRepository.findById(reportDto.getLovehomeId()).get());
		reportRepository.save(reportList);
		return mapper.toReportListDto(reportList);
	}

	@Override
	public List<ReportListDto> getAllReport() {
		return reportRepository.findAll().stream().map(mapper::toReportListDto).collect(Collectors.toList());
	}

	@Override
	public ReportListDto getReportByNumber(Integer reportNumber) {
		ReportList report = reportRepository.findById(reportNumber)
				.orElseThrow(() -> new ReportNotFoundException("無此通報表單：" + reportNumber));
		return mapper.toReportListDto(report);
	}

	@Override
	public ReportListDto updateReport(ReportListDto reportDto) {
		ReportList updateReportList = reportRepository.findById(reportDto.getReportNumber()).get();
		updateReportList.setReportStatus(reportDto.getReportStatus());
		reportRepository.save(updateReportList);
		return mapper.toReportListDto(updateReportList);
	}

	@Override
	public void deleteReport(Integer reportNumber) {
		Optional<ReportList> optReportList = reportRepository.findById(reportNumber);
		if (optReportList.isEmpty()) {
			throw new ReportNotFoundException("無此通報清單:" + reportNumber);
		}
		reportRepository.deleteById(reportNumber);
	}

	@Override
	public List<ReportListDto> getReportByUserId(Integer userId) {
		return reportRepository.findByUserUserId(userId).stream()
				.map(mapper::toReportListDto).collect(Collectors.toList());
	}

	@Override
	public List<ReportListDto> getReportByLovehomeId(Integer lovehomeId) {
		List<ReportListDto> reportListDtos = reportRepository.findByLovehomeLovehomeId(lovehomeId).stream()
				.map(mapper::toReportListDto).collect(Collectors.toList());
		return reportListDtos;
	}
}
