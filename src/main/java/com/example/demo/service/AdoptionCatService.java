package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.CatDto;

public interface AdoptionCatService {
	public CatDto addCat(CatDto catDto, MultipartFile photoFile);
	public List<CatDto> findAllCats();
	public List<CatDto> findAllAdoptionCats();
	public CatDto getCatById(Integer catId);
	public CatDto updateCat(CatDto catDto, MultipartFile photoFile);
	public CatDto updateCatWithoutPhoto(CatDto catDto);
	public void deleteCatById(Integer catId);
}
