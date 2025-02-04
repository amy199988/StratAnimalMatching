package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.LovehomeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.model.entity.Lovehome;
import com.example.demo.repository.LovehomeRepository;
import com.example.demo.service.LovehomeService;

@Service
public class LovehomeServiceImpl implements LovehomeService {

	@Autowired
	private LovehomeRepository lovehomeRepository;

	@Autowired
	private Mapper objectMapper;

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
	public LovehomeDto updateLovehome(LovehomeDto lovehomeDto) {
		Lovehome lovehome = objectMapper.toLovehomeEntity(lovehomeDto);
		lovehomeRepository.save(lovehome);
		return objectMapper.toLovehomeDto(lovehome);
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
	public void updateCurrentOccupancy(Integer lovehomeId) {
		Lovehome lovehome = lovehomeRepository.findById(lovehomeId)
				.orElseThrow(() -> new LovehomeNotFoundException("找不到中途:lovehomeId" + lovehomeId));
		lovehome.setCurrentOccupancy((lovehome.getCats().size() / lovehome.getCapacity())*100);
		lovehomeRepository.save(lovehome);
	}
}
