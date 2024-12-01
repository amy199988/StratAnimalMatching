package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.ReportList;

public interface ReportRepository extends JpaRepository<ReportList, Integer> {

}
