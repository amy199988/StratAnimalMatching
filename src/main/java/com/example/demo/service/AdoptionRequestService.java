package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.AdoptionRequestDto;

public interface AdoptionRequestService {
	public AdoptionRequestDto addAdoptionRequest(AdoptionRequestDto adoptionRequestDto);
	public AdoptionRequestDto updateAdoptionRequest(AdoptionRequestDto adoptionRequestDto);
	public List<AdoptionRequestDto> findAllRequests();
	public List<AdoptionRequestDto> getRequestsByuserId(Integer userId);
	public List<AdoptionRequestDto> getRequestsBylovehomeId(Integer lovehomeId);
	public AdoptionRequestDto getRequestByNumber(Integer requestDtoNumber);
	public void deleteRequestByNumber(Integer requestDtoNumber);
}
