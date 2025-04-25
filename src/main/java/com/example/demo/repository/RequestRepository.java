package com.example.demo.repository;

import com.example.demo.model.entity.AdoptionRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RequestRepository extends JpaRepository<AdoptionRequest, Integer> {

  List<AdoptionRequest> findByUserUserId(Integer userId);

  List<AdoptionRequest> findByCatLovehomeLovehomeId(Integer lovehomeId);
}
