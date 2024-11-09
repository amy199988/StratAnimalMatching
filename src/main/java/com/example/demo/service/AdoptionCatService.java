package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ObjectMapper;
import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.repository.CatRepository;

@Service
public class AdoptionCatService {
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	// 新增領養貓咪
	public Cat appendCat(String catname, String breed, Integer age, 
						  String healthstatus, String description, 
						  String photoUrl, Boolean isapply) {
		Cat cat = new Cat();
		cat.setCatname(catname);
		cat.setBreed(breed);
		cat.setAge(age);
		cat.setHealthStatus(healthstatus);
		cat.setDescription(description);
		cat.setCatphoto_url(photoUrl);
		cat.setIsapply(isapply);
		
		catRepository.save(cat);
		return cat;
	}
	
	// 查詢全部貓咪資訊
	public List<CatDto> FindAllCat() {
		List<CatDto> catDtos = new ArrayList<>();
		List<Cat> cats = catRepository.findAll();
		for(Cat cat : cats) {
			CatDto catDto = new CatDto();
		 	catDto = objectMapper.toCatDto(cat);
			catDtos.add(catDto);
		}
		return catDtos;
	}
	
	// 查詢全部可領養貓咪資訊
		public List<CatDto> FindAllAdoptionCat() {
			List<CatDto> catDtos = new ArrayList<>();
			List<Cat> cats = catRepository.findAllByIsapplyTrue();
			for(Cat cat : cats) {
				CatDto catDto = new CatDto();
			 	catDto = objectMapper.toCatDto(cat);
				catDtos.add(catDto);
			}
			return catDtos;
		}
	
	// 查詢貓咪資訊 By CatId
	public CatDto FindCatById(Integer catId) {
		Optional<Cat> cat = catRepository.findById(catId);
		if(cat == null) {
			return null;
		}
		CatDto catDto = new CatDto();
		catDto = objectMapper.toCatDto(cat.get());
		return catDto;
	}
	
	// 修改貓咪資訊
	public void updateCat(Integer catId,String catname, String breed, Integer age, 
			  			  String healthstatus, String description, 
			  			  String photoUrl, Boolean isapply) {
		Cat cat = new Cat();
		cat.setCatId(catId);
		cat.setCatname(catname);
		cat.setBreed(breed);
		cat.setAge(age);
		cat.setHealthStatus(healthstatus);
		cat.setDescription(description);
		cat.setCatphoto_url(photoUrl);
		cat.setIsapply(isapply);
		
		catRepository.save(cat);
	}
	
	// 刪除貓咪資訊 By CatId
	public void DeleteCatById(Integer catId) {
		catRepository.deleteById(catId);
	}
	
}
