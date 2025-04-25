package com.example.demo.repository;

import com.example.demo.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByAccount(String account);

  User getByAccount(String account);

  Optional<User> findByLovehomeLovehomeId(Integer lovehomeId);

  Optional<User> findByEmail(String email);

  Optional<User> findByResetToken(String token);
}
