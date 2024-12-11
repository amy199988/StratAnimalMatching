package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AdoptionNotFoundException;
import com.example.demo.exception.RequestNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.AdoptionRequestDto;
import com.example.demo.model.entity.AdoptionRequest;
import com.example.demo.repository.CatRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdoptionRequestService;

@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService{
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public AdoptionRequestDto addAdoptionRequest(AdoptionRequestDto adoptionRequestDto) {
		AdoptionRequest adoptionRequest = mapper.toAdoptionRequestEntity(adoptionRequestDto);
		adoptionRequest.setCat(catRepository.findById(adoptionRequestDto.getCatDto().getCatId()).get());
		adoptionRequest.setUser(userRepository.findById(adoptionRequestDto.getUserDto().getUserId()).get());
		requestRepository.save(adoptionRequest);
		return mapper.toAdoptionRequestDto(adoptionRequest);
	}

	@Override
	public AdoptionRequestDto updateAdoptionRequest(AdoptionRequestDto adoptionRequestDto) {
		AdoptionRequest updateAdoptionRequest = requestRepository.findById(adoptionRequestDto.getRequestNumber()).get();
		updateAdoptionRequest.setRequestStatus(adoptionRequestDto.getRequestStatus());
		requestRepository.save(updateAdoptionRequest);
		return mapper.toAdoptionRequestDto(updateAdoptionRequest);
	}

	@Override
	public List<AdoptionRequestDto> findAllRequests() {
		return requestRepository.findAll().stream().map(mapper::toAdoptionRequestDto).collect(Collectors.toList());
	}

	@Override
	public AdoptionRequestDto getRequestByNumber(Integer requestDtoNumber) {
		AdoptionRequest adoptionRequest = requestRepository.findById(requestDtoNumber)
				.orElseThrow(() -> new AdoptionNotFoundException("找不到清單:requestNumber" + requestDtoNumber));
		return mapper.toAdoptionRequestDto(adoptionRequest);
	}

	@Override
	public List<AdoptionRequestDto> getRequestsByuserId(Integer userId) {
		return requestRepository.findByUserUserId(userId).stream().map(mapper::toAdoptionRequestDto).collect(Collectors.toList());
	}

	@Override
	public List<AdoptionRequestDto> getRequestsBylovehomeId(Integer lovehomeId) {
		List<AdoptionRequestDto> adoptionRequestDtos = requestRepository.findByCatLovehomeLovehomeId(lovehomeId).stream().map(mapper::toAdoptionRequestDto).collect(Collectors.toList());
		return adoptionRequestDtos;
	}

	@Override
	public void deleteRequestByNumber(Integer requestDtoNumber) {
		Optional<AdoptionRequest> optAdoptionRequest = requestRepository.findById(requestDtoNumber);
		if (optAdoptionRequest.isEmpty()) {
			throw new RequestNotFoundException("無此領養清單:" + requestDtoNumber);
		}
		requestRepository.deleteById(requestDtoNumber);
	}

}
