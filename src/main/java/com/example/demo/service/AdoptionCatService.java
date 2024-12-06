package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.CatDto;

public interface AdoptionCatService {
	public CatDto addCat(CatDto catDto, Integer lovehomeId);
	public List<CatDto> findAllCats();
	public List<CatDto> findAllAdoptionCats();
	public CatDto getCatById(Integer catId);
	public CatDto updateCat(CatDto catDto);
	public void deleteCatById(Integer catId);
}
