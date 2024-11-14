package com.example.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepositoryJdbc;


@Repository
//@PropertySource("")
public class UserRepositoryJdbcImpl implements UserRepositoryJdbc {

	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<User> findAllUsers() {
		return jdbcTemplate.query("sql", new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public Optional<User> findByAccount(Integer account) {
		try {
			User user = jdbcTemplate.queryForObject("sql", new BeanPropertyRowMapper<>(User.class), account);
			return Optional.of(user) ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Optional.empty();
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteById(Integer userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateUserActive(Integer userId, Boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePasswordHash(Integer userId, String newPasswordHash) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
