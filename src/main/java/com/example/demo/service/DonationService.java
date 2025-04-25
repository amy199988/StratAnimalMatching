package com.example.demo.service;

import com.example.demo.model.dto.DonationDto;
import com.example.demo.model.entity.Donation;
import java.util.List;

public interface DonationService {

  public Donation addDonation(Donation donation);

  public List<DonationDto> findAllDonations();

  public DonationDto getDonationById(Integer donationId);
}
