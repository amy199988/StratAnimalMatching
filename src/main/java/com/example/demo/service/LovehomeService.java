package com.example.demo.service;

import java.util.List;
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

	//查詢中途-依城市
	public List<LovehomeDto> getlovehomeCity(String lovehomeCity);
	//查詢中途-依城市、區域
	//public List<LovehomeDto> getlovehomeCityAndDistrict(String lovehomeCity,String lovehomeDistrict);
	
	//查詢中途-依關鍵詞
	//public List<LovehomeDto> findByNameContaining(String keyword);

}
