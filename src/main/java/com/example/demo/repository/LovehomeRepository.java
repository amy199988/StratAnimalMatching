package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Lovehome;
import java.util.List;


@Repository
public interface LovehomeRepository extends JpaRepository<Lovehome, Integer>{
	List<Lovehome> findByLovehomeCity(String lovehomeCity);
	List<Lovehome> findByLovehomeDistrict(String lovehomeCity,String lovehomeDistrict);
	List<Lovehome> findByNameContaining(String keyword);
}
