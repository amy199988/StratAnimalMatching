package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>{

}