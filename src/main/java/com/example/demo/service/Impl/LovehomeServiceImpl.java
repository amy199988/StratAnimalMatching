package com.example.demo.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.LovehomeNotFoundException;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.entity.Lovehome;
import com.example.demo.repository.LovehomeRepository;
import com.example.demo.service.LovehomeService;
import com.example.demo.util.Imgur;

@Service
public class LovehomeServiceImpl implements LovehomeService {

	@Autowired
	private LovehomeRepository lovehomeRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private Imgur imgur;
	
	// 新增中途之家
	@Override
	public Lovehome addLovehome(Lovehome lovehome, MultipartFile photoFile) {
		if(photoFile == null || photoFile.isEmpty()) {
			throw new LovehomeNotFoundException("找不到照片資料:photoFile" + photoFile);
		}
		
		lovehome.setLovehome_Url(imgur.uploadImage(photoFile));
		lovehomeRepository.save(lovehome);
		return lovehome;
	}

	@Override
	public List<LovehomeDto> findAllLovehomes() {
		return lovehomeRepository.findAll()
				.stream()
				.map(objectMapper::toLovehomeDto)
				.collect(Collectors.toList());
	}

	@Override
	public LovehomeDto getLovehomeById(Integer lovehomeId) {
		Lovehome lovehome = lovehomeRepository.findById(lovehomeId)
							.orElseThrow(() -> new LovehomeNotFoundException("找不到中途:lovehomeId" + lovehomeId));
		lovehome.setCurrentOccupancy((lovehome.getCats().size() / lovehome.getCapacity()));
		return objectMapper.toLovehomeDto(lovehome);
	}

	@Override
	public Lovehome updateLovehome(Lovehome lovehome, MultipartFile photoFile) {
		lovehome.setLovehome_Url(imgur.uploadImage(photoFile));
		lovehomeRepository.save(lovehome);
		return lovehome;
	}

	@Override
	public Lovehome updateLovehomeWithoutPhoto(Lovehome lovehome) {
		Lovehome updatelovehome = lovehomeRepository.findById(lovehome.getLovehomeId())
				.orElseThrow(() -> new LovehomeNotFoundException("找不到中途:lovehomeId" + lovehome.getLovehomeId()));
		updatelovehome.setLovehomeName(lovehome.getLovehomeName());
		updatelovehome.setLovehomeCity(lovehome.getLovehomeCity());
		updatelovehome.setLovehomeDistrict(lovehome.getLovehomeDistrict());
		updatelovehome.setLovehomeAddress(lovehome.getLovehomeAddress());
		lovehomeRepository.save(updatelovehome);
		return updatelovehome;
	}

	@Override
	public void deleteLovehomeById(Integer lovehomeId) {
		lovehomeRepository.deleteById(lovehomeId);
	}

	@Override
	public List<LovehomeDto> getlovehomeCity(String lovehomeCity) {
		return lovehomeRepository.findByLovehomeCity(lovehomeCity)
				.stream()
				.map(objectMapper::toLovehomeDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<LovehomeDto> getlovehomeDistrict(String lovehomeCity, String lovehomeDistrict) {
		return lovehomeRepository.findByLovehomeDistrict(lovehomeCity, lovehomeDistrict)
				.stream()
				.map(objectMapper::toLovehomeDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<LovehomeDto> searchLovehomeByName(String keyword) {
		List<Lovehome> lovehome = lovehomeRepository.findByNameContaining(keyword);
		if(lovehome.isEmpty()) {
			throw new LovehomeNotFoundException("找不到中途:keyword" + keyword);
		}
		return lovehome.stream()
				.map(objectMapper::toLovehomeDto)
				.collect(Collectors.toList());
	}

}
