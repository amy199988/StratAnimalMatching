package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.DonationDto;
import com.example.demo.model.entity.Donation;
import com.example.demo.repository.DonationRepository;
import com.example.demo.service.DonationService;

public class DonationServiceImpl implements DonationService {

	@Autowired
	private DonationRepository donationRepository;
	
	@Autowired
	private Mapper objectMapper;
	
	// 新增捐贈清單
	@Override
	public Donation addDonation(Donation donation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DonationDto> findAllDonations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DonationDto getDonationById(Integer donationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
