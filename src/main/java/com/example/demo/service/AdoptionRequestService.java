package com.example.demo.service;

import com.example.demo.model.dto.AdoptionRequestDto;
import java.util.List;

public interface AdoptionRequestService {

  public AdoptionRequestDto addAdoptionRequest(AdoptionRequestDto adoptionRequestDto);

  public AdoptionRequestDto updateAdoptionRequest(AdoptionRequestDto adoptionRequestDto);

  public List<AdoptionRequestDto> findAllRequests();

  public List<AdoptionRequestDto> getRequestsByuserId(Integer userId);

  public List<AdoptionRequestDto> getRequestsBylovehomeId(Integer lovehomeId);

  public AdoptionRequestDto getRequestByNumber(Integer requestDtoNumber);

  public void deleteRequestByNumber(Integer requestDtoNumber);
}
