package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Autowired
	private ImgurService imgurService;
	
	// 新增領養貓咪
	public Cat appendCat(Cat cat, MultipartFile photofFile) {
		if (photofFile == null || photofFile.isEmpty()) {
			System.out.println("photoFile資料為空或null");
	    }
		
		String photoUrl = imgurService.uploadImage(photofFile);
		
		cat.setCatphoto_url(photoUrl);
		System.out.println("Uploaded Image URL: " + photoUrl);
		
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
	public Cat updateCat(Cat cat, MultipartFile photofFile) {
		cat.setCatphoto_url(imgurService.uploadImage(photofFile));
		catRepository.save(cat);
		return cat;
	}
	
	// 刪除貓咪資訊 By CatId
	public void DeleteCatById(Integer catId) {
		catRepository.deleteById(catId);
	}
	
}
