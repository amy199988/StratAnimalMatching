package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.CatDto;
import com.example.demo.model.dto.LovehomeDto;

public interface LovehomeService {
	
	public List<LovehomeDto> findAllLovehomes();
	public LovehomeDto getLovehomeById(Integer lovehomeId);
	public void deleteLovehomeById(Integer lovehomeId);
	
	public LovehomeDto updateLovehome(LovehomeDto lovehomeDto);

	//查詢中途-依城市
	public List<LovehomeDto> getlovehomeCity(String lovehomeCity);
	
	//查詢中途-依城市、區域
	public List<LovehomeDto> getlovehomeCityAndDistrict(String lovehomeCity,String lovehomeDistrict);
	
	public List<CatDto> getLovehomecatList(Integer lovehomeId);
	
	//更新目前占用率
	public LovehomeDto updateCurrentOccupancy(Integer lovehomeId);
}
