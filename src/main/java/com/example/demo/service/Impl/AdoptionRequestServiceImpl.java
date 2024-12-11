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
import com.example.demo.repository.RequestRepository;
import com.example.demo.service.AdoptionRequestService;

@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService{
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public AdoptionRequestDto addAdoptionRequest(AdoptionRequestDto adoptionRequestDto) {
		AdoptionRequest adoptionRequest = mapper.toAdoptionRequestEntity(adoptionRequestDto);
		adoptionRequest.setCat(mapper.toCatEntity(adoptionRequestDto.getCatDto()));
		adoptionRequest.setUser(mapper.toUserEntity(adoptionRequestDto.getUserDto()));
		requestRepository.save(adoptionRequest);
		return mapper.toAdoptionRequestDto(adoptionRequest);
	}

	@Override
	public AdoptionRequestDto updateAdoptionRequest(AdoptionRequestDto adoptionRequestDto) {
		return requestRepository.findById(adoptionRequestDto.getRequestNumber())
				.map(adoptionrequest -> {
					mapper.toAdoptionRequestEntity(adoptionRequestDto);
					AdoptionRequest upAdoptionRequest = requestRepository.save(adoptionrequest);
					return mapper.toAdoptionRequestDto(upAdoptionRequest);
				})
				.orElseThrow(() -> new RequestNotFoundException("查無資料"));
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
