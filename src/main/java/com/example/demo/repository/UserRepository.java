package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.entity.User;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
	Optional <User> findByAccount(String account);
	User getByAccount(String account);
}