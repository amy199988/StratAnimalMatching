package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.DonationDto;
import com.example.demo.model.entity.Donation;

public interface DonationService {
	public Donation addDonation(Donation donation);
	public List<DonationDto> findAllDonations();
	public DonationDto getDonationById(Integer donationId);
}
