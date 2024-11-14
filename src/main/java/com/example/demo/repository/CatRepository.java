package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer>{
	List<Cat> findAllByIsApplyTrue();
}
