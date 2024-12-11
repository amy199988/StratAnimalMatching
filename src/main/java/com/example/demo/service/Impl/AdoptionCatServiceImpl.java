package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AdoptionNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.repository.CatRepository;
import com.example.demo.repository.LovehomeRepository;
import com.example.demo.service.AdoptionCatService;

@Service
public class AdoptionCatServiceImpl implements AdoptionCatService {

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private LovehomeRepository lovehomeRepository;

	@Autowired
	private Mapper objectMapper;

	// 新增領養貓咪
	@Override
	public CatDto addCat(CatDto catDto, Integer lovehomeId) {
		Cat cat = objectMapper.toCatEntity(catDto);
		cat.setLovehome(lovehomeRepository.findById(lovehomeId).get());
		catRepository.save(cat);
		return objectMapper.toCatDto(cat);
	}

	// 查詢全部貓咪資訊
	@Override
	public List<CatDto> findAllCats() {
		return catRepository.findAll().stream().map(objectMapper::toCatDto).collect(Collectors.toList());
	}

	// 查詢全部可領養貓咪資訊
	@Override
	public List<CatDto> findAllAdoptionCats() {
		return catRepository.findAllByIsApplyTrue()
				.stream()
				.map(cat -> {
					CatDto catDto = objectMapper.toCatDto(cat);
					if (cat.getLovehome() != null) {
						catDto.setLovehomeId(cat.getLovehome().getLovehomeId());
	                    catDto.setLovehomeName(cat.getLovehome().getLovehomeName());
	                }
	                return catDto;
				})
				.collect(Collectors.toList());
	}

	// 查詢貓咪資訊 By CatId
	@Override
	public CatDto getCatById(Integer catId) {
		// 判斷該貓咪是否存在 ?
		Cat cat = catRepository.findById(catId).orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + catId));
		return objectMapper.toCatDto(cat);
	}

	// 修改貓咪資訊
	@Override
	public CatDto updateCat(CatDto catDto) {
		Integer catId = catDto.getCatId();
		Cat cat = catRepository.findById(catId).orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + catId));
		Cat updatecat = objectMapper.toCatEntity(catDto);
		updatecat.setLovehome(cat.getLovehome());
		catRepository.save(updatecat);
		return objectMapper.toCatDto(updatecat);
	}

	// 刪除貓咪資訊 ByCatId
	@Override
	public void deleteCatById(Integer catId) {
		Optional<Cat> optCat = catRepository.findById(catId);
		if (optCat.isEmpty()) {
			throw new AdoptionNotFoundException("無此貓咪:" + catId);
		}
		catRepository.deleteById(catId);
	}
}
