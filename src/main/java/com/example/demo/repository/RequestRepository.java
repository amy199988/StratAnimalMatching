package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.AdoptionRequest;
import com.example.demo.model.entity.User;


public interface RequestRepository extends JpaRepository<AdoptionRequest, Integer>{
	List<AdoptionRequest> findByUserUserId(Integer userId);
	List<AdoptionRequest> findByCatLovehomeLovehomeId(Integer lovehomeId);
}
