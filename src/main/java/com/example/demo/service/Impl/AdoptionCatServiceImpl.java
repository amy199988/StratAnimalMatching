package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.AdoptionNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.repository.CatRepository;
import com.example.demo.repository.LovehomeRepository;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.util.Imgur;

@Service
public class AdoptionCatServiceImpl implements AdoptionCatService{
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private LovehomeRepository lovehomeRepository;
	
	@Autowired
	private Mapper objectMapper;
	
	@Autowired
	private Imgur imgur;
	
	// 新增領養貓咪
	@Override
	public Cat addCat(Cat cat, MultipartFile photoFile) {
		// 判斷照片資料是否存在
		if (photoFile == null || photoFile.isEmpty()) {
			throw new AdoptionNotFoundException("找不到照片資料:photoFile" + photoFile);
	    }
		
		cat.setCatphoto_Url(imgur.uploadImage(photoFile));
		cat.setLovehome(lovehomeRepository.findById(1).get());
		catRepository.save(cat);
		return cat;
	}
	
	@Override
	public CatDto addCat(CatDto catDto, MultipartFile photoFile) {
		Cat cat = objectMapper.toCatEntity(catDto);
		// 判斷照片資料是否存在
		if (photoFile == null || photoFile.isEmpty()) {
			throw new AdoptionNotFoundException("找不到照片資料:photoFile" + photoFile);
	    }
		
		cat.setCatphoto_Url(imgur.uploadImage(photoFile));
		cat.setLovehome(lovehomeRepository.findById(1).get());
		catRepository.save(cat);
		return objectMapper.toCatDto(cat);
	}
	
	// 查詢全部貓咪資訊
	@Override
	public List<CatDto> findAllCats() {
		/*
		List<CatDto> catDtos = new ArrayList<>();
		List<Cat> cats = catRepository.findAll();
		for(Cat cat : cats) {
			CatDto catDto = new CatDto();
		 	catDto = objectMapper.toCatDto(cat);
			catDtos.add(catDto);
		}
		return catDtos;
		*/
		return catRepository.findAll()
				.stream()
				.map(objectMapper::toCatDto)
				.collect(Collectors.toList());
	}
	
	// 查詢全部可領養貓咪資訊
	@Override
	public List<CatDto> findAllAdoptionCats() {
		/*
		List<CatDto> catDtos = new ArrayList<>();
		List<Cat> cats = catRepository.findAllByIsapplyTrue();
		for(Cat cat : cats) {
			CatDto catDto = new CatDto();
			 catDto = objectMapper.toCatDto(cat);
			catDtos.add(catDto);
		}
		return catDtos;
		*/
		return catRepository.findAllByIsApplyTrue()
				.stream()
				.map(objectMapper::toCatDto)
				.collect(Collectors.toList());
	}
	
	// 查詢貓咪資訊 By CatId
	@Override
	public CatDto getCatById(Integer catId) {
		/*
		Optional<Cat> cat = catRepository.findById(catId);
		if(cat == null) {
			return null;
		}
		CatDto catDto = new CatDto();
		catDto = objectMapper.toCatDto(cat.get());
		return catDto;
		*/
		// 判斷該貓咪是否存在 ?
		Cat cat = catRepository.findById(catId)
				.orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + catId));
		return objectMapper.toCatDto(cat);
	}
	
	// 修改貓咪資訊
	@Override
	public Cat updateCat(Cat cat, MultipartFile photoFile) {
		Cat updateCat = catRepository.findById(cat.getCatId())
				.orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + cat.getCatId()));
		updateCat.setCatphoto_Url(imgur.uploadImage(photoFile));	
		catRepository.save(updateCat);
		return updateCat;
	}
	
	// 修改貓咪資訊除外PhotoUrl
	@Override
	public Cat updateCatWithoutPhoto(Cat cat) {
		Cat updateCat = catRepository.findById(cat.getCatId())
				.orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + cat.getCatId()));
		updateCat.setCatName(cat.getCatName());
		updateCat.setBreed(cat.getBreed());
		updateCat.setAge(cat.getAge());
		updateCat.setHealthStatus(cat.getHealthStatus());
		updateCat.setDescription(cat.getDescription());
		updateCat.setIsApply(cat.getIsApply());
		catRepository.save(updateCat);
		return updateCat;
	}	
	
	// 刪除貓咪資訊 ByCatId
	@Override
	public void deleteCatById(Integer catId) {
		Optional<Cat> optCat = catRepository.findById(catId);
		if (optCat.isEmpty()) {
			
		}
		catRepository.deleteById(catId);
	}
	
	
	// 修改貓咪資訊
	@Override
	public CatDto updateCat(CatDto catDto, MultipartFile photoFile) {
		Cat cat = objectMapper.toCatEntity(catDto);
		Cat updateCat = catRepository.findById(cat.getCatId())
				.orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + cat.getCatId()));
		updateCat.setCatphoto_Url(imgur.uploadImage(photoFile));	
		catRepository.save(updateCat);
		return objectMapper.toCatDto(updateCat);
	}
	
	// 修改貓咪資訊除外PhotoUrl
	@Override
	public CatDto updateCatWithoutPhoto(CatDto catDto) {
		Cat cat = objectMapper.toCatEntity(catDto);
		Cat updateCat = catRepository.findById(cat.getCatId())
				.orElseThrow(() -> new AdoptionNotFoundException("找不到貓咪:catId" + cat.getCatId()));
		updateCat.setCatName(cat.getCatName());
		updateCat.setBreed(cat.getBreed());
		updateCat.setAge(cat.getAge());
		updateCat.setHealthStatus(cat.getHealthStatus());
		updateCat.setDescription(cat.getDescription());
		updateCat.setIsApply(cat.getIsApply());
		catRepository.save(updateCat);
		return objectMapper.toCatDto(updateCat);
	}	
}
