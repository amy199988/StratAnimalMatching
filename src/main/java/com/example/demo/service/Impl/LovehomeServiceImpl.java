package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.LovehomeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.model.entity.Lovehome;
import com.example.demo.model.entity.ReportList;
import com.example.demo.repository.LovehomeRepository;
import com.example.demo.service.LovehomeService;
import com.example.demo.util.Imgur;

@Service
public class LovehomeServiceImpl implements LovehomeService {

	@Autowired
	private LovehomeRepository lovehomeRepository;

	@Autowired
	private Mapper objectMapper;

	@Autowired
	private Imgur imgur;

	@Override
	public List<LovehomeDto> findAllLovehomes() {
		return lovehomeRepository.findAll().stream().map(objectMapper::toLovehomeDto).collect(Collectors.toList());
	}

	@Override
	public LovehomeDto getLovehomeById(Integer lovehomeId) {
		Lovehome lovehome = lovehomeRepository.findById(lovehomeId)
				.orElseThrow(() -> new LovehomeNotFoundException("找不到中途:lovehomeId" + lovehomeId));
		LovehomeDto lovehomeDto = objectMapper.toLovehomeDto(lovehome);
		List<CatDto> catDtos = lovehome.getCats().stream().map(objectMapper::toCatDto).collect(Collectors.toList());
		lovehomeDto.setCatDtos(catDtos);
		return lovehomeDto;
	}

	@Override
	public void deleteLovehomeById(Integer lovehomeId) {
		Optional<Lovehome> lovehome = lovehomeRepository.findById(lovehomeId);
		if (lovehome.isEmpty()) {
			throw new LovehomeNotFoundException("刪除失敗：" + lovehomeId + "不存在");
		}
		lovehomeRepository.deleteById(lovehomeId);
	}

	@Override
	public LovehomeDto updateLovehome(LovehomeDto lovehomeDto, MultipartFile photoFile) {
		Lovehome lovehome = objectMapper.toLovehomeEntity(lovehomeDto);
		lovehome.setLovehome_Url(imgur.uploadImage(photoFile));
		lovehomeRepository.save(lovehome);
		return objectMapper.toLovehomeDto(lovehome);
	}

	@Override
	public LovehomeDto updateLovehomeWithoutPhoto(LovehomeDto lovehomeDto) {
		Lovehome lovehome = objectMapper.toLovehomeEntity(lovehomeDto);
		Lovehome updatelovehome = lovehomeRepository.findById(lovehome.getLovehomeId())
				.orElseThrow(() -> new LovehomeNotFoundException("找不到中途:lovehomeId" + lovehome.getLovehomeId()));
		updatelovehome.setLovehomeName(lovehome.getLovehomeName());
		updatelovehome.setLovehomeCity(lovehome.getLovehomeCity());
		updatelovehome.setLovehomeDistrict(lovehome.getLovehomeDistrict());
		updatelovehome.setLovehomeAddress(lovehome.getLovehomeAddress());
		updatelovehome.setContactInfo(lovehome.getContactInfo());
		updatelovehome.setCapacity(lovehome.getCapacity());
		lovehomeRepository.save(updatelovehome);
		return objectMapper.toLovehomeDto(updatelovehome);
	}

	@Override
	public List<LovehomeDto> getlovehomeCity(String lovehomeCity) {
		return lovehomeRepository.findByLovehomeCity(lovehomeCity).stream().map(objectMapper::toLovehomeDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<LovehomeDto> getlovehomeCityAndDistrict(String lovehomeCity, String lovehomeDistrict) {
		return lovehomeRepository.findByLovehomeCityAndLovehomeDistrict(lovehomeCity, lovehomeDistrict).stream()
				.map(objectMapper::toLovehomeDto).collect(Collectors.toList());
	}

	@Override
	// 查詢所有上傳的貓
	public List<CatDto> getLovehomecatList(Integer lovehomeId) {
		Lovehome lovehome = lovehomeRepository.findById(lovehomeId).orElseThrow(() -> new UserNotFoundException());
		List<Cat> cats = lovehome.getCats();
		return cats.stream().map(cat -> objectMapper.toCatDto(cat)).toList();
	}

	@Override
	// 查詢中途之家收到的通報清單
	public List<ReportListDto> getLovehomeReportList(Integer lovehomeId) {
		Lovehome lovehome = lovehomeRepository.findById(lovehomeId).orElseThrow(() -> new UserNotFoundException());
		List<ReportList> reportLists = lovehome.getReportLists();
		return reportLists.stream().map(report -> objectMapper.toReportListDto(report)).toList();
	}

	@Override
	public LovehomeDto updateCurrentOccupancy(Integer lovehomeId) {
		Lovehome lovehome = lovehomeRepository.findById(lovehomeId)
				.orElseThrow(() -> new LovehomeNotFoundException("找不到中途:lovehomeId" + lovehomeId));
		lovehome.setCurrentOccupancy((lovehome.getCats().size() / lovehome.getCapacity()));
		LovehomeDto lovehomeDto = objectMapper.toLovehomeDto(lovehome);
		return lovehomeDto;
	}
}
