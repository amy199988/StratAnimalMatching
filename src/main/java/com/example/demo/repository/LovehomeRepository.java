package com.example.demo.repository;

import com.example.demo.model.entity.Lovehome;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LovehomeRepository extends JpaRepository<Lovehome, Integer> {

  List<Lovehome> findByLovehomeCity(String lovehomeCity);

  List<Lovehome> findByLovehomeCityAndLovehomeDistrict(String lovehomeCity,
      String lovehomeDistrict);

}
