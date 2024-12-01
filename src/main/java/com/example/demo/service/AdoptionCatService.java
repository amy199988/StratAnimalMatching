package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;

public interface AdoptionCatService {
	public Cat addCat(Cat cat, MultipartFile photoFile);
	public List<CatDto> findAllCats();
	public List<CatDto> findAllAdoptionCats();
	public CatDto getCatById(Integer catId);
	public Cat updateCat(Cat cat, MultipartFile photoFile);
	public Cat updateCatWithoutPhoto(Cat cat);
	public void deleteCatById(Integer catId);
	
	public CatDto addCat(CatDto catDto, MultipartFile photoFile);
	public CatDto updateCat(CatDto catDto, MultipartFile photoFile);
	public CatDto updateCatWithoutPhoto(CatDto catDto);
}
