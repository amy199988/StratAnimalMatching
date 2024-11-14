package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.AdoptionNotFoundException;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.repository.CatRepository;
import com.example.demo.service.AdoptionCat;
import com.example.demo.util.Imgur;

@Service
public class AdoptionCatServiceImpl implements AdoptionCat{
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private Imgur imgur;
	
	// 新增領養貓咪
	public Cat addCat(Cat cat, MultipartFile photoFile) {
		// 判斷照片資料是否存在
		if (photoFile == null || photoFile.isEmpty()) {
			throw new AdoptionNotFoundException("找不到照片資料:photoFile" + photoFile);
	    }
		
		cat.setCatphoto_Url(imgur.uploadImage(photoFile));
		catRepository.save(cat);
		return cat;
	}
	
	// 查詢全部貓咪資訊
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
		return catRepository.findAllByIsapplyTrue()
				.stream()
				.map(objectMapper::toCatDto)
				.collect(Collectors.toList());
	}
	
	// 查詢貓咪資訊 By CatId
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
	public Cat updateCat(Cat cat, MultipartFile photoFile) {
		if(photoFile == null || photoFile.isEmpty()) {
			catRepository.save(cat);
			return cat;
		}
		cat.setCatphoto_Url(imgur.uploadImage(photoFile));	
		catRepository.save(cat);
		return cat;
	}
	
	// 修改貓咪資訊除外PhotoUrl
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
	public void deleteCatById(Integer catId) {
		catRepository.deleteById(catId);
	}
	
}
