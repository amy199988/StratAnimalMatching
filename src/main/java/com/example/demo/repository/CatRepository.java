package com.example.demo.repository;

import com.example.demo.model.entity.Cat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

  List<Cat> findAllByIsApplyTrue();
}
