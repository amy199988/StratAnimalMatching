package com.example.demo.service;

import java.util.List;

<<<<<<< Updated upstream
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.entity.Lovehome;

public interface LovehomeService {
	public Lovehome addLovehome(Lovehome lovehome, MultipartFile photoFile);
	public List<LovehomeDto> findAllLovehomes();
	public LovehomeDto getLovehomeById(Integer lovehomeId);
	public Lovehome updateLovehome(Lovehome lovehome, MultipartFile photoFile);
	public Lovehome updateLovehomeWithoutPhoto(Lovehome lovehome);
	public void deleteLovehomeById(Integer lovehomeId);
=======
import com.example.demo.model.dto.LovehomeDto;

public interface LovehomeService {
	
	//查詢所有愛媽清冊
	public List<LovehomeDto> getAllLovehomes();	
		
	//查詢愛媽-依城市
	public List<LovehomeDto> getlovehomeCity(String lovehomeCity);
		
	//查詢愛媽-依城市、區域
	public List<LovehomeDto> getlovehomeDistrict(String lovehomeCity,String lovehomeDistrict);
		
	//查詢愛媽-依地址
	public List<LovehomeDto> getlovehomeAddress(String lovehomeAddress);
	
	//查詢單筆愛媽
	public LovehomeDto getLovehomeByName(String lovehomeName);
	
	//新增愛媽使用者
	public void addLoveHomeUser(LovehomeDto lovehomeDto);
	
	//修改愛媽使用者
	public void updateLoveHomeUser(Integer lovehomeId,LovehomeDto lovehomeDto);

>>>>>>> Stashed changes
}
