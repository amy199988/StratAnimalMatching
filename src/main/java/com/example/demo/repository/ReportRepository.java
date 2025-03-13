package com.example.demo.repository;

import com.example.demo.model.entity.ReportList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportList, Integer> {

  List<ReportList> findByUserUserId(Integer userId);

  List<ReportList> findByLovehomeLovehomeId(Integer lovehomeId);
}
