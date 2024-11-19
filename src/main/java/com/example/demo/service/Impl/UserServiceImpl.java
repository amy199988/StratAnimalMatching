package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PasswordInvalidException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private Mapper mapper;


	@Override
	public void addUser(UserDto userDto, String password) {
		User account = userRepository.getByAccount(userDto.getAccount());
		if (account != null) {
			throw new UserNotFoundException("新增失敗"+ userDto.getAccount()+"已存在");
			}
		
		String salt = Hash.getSalt();
		String passswordHash = Hash.getHash(password,salt);
		Boolean active = false;
		
		User user = objectMapper.convertValue(userDto,User.class);
        user.setSalt(salt);
		user.setPasswordHash(passswordHash);
		user.setActive(active);
		userRepository.save(user);
		
//      user.setUserId(userDto.getUserId());
//		user.setUserName(userDto.getUserName());
//		user.setAccount(userDto.getAccount());
//		user.setSalt(salt);
//		user.setPasswordHash(passswordHash);
//		user.setPhone(userDto.getPhone());
//		user.setBirthdate(userDto.getBirthdate());
//		user.setEmail(userDto.getEmail());
//		user.setRole(userDto.getRole());	
	}
		

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("無此使用者userId：" + userId));
		return mapper.toUserDto(user);
	}

	@Override
	public UserDto getUserByAccount(String account) {
		User user = userRepository.getByAccount(account);
			if (user == null) {
				throw new UserNotFoundException("無此使用者account：" + account);
				}
		return mapper.toUserDto(user);	
	}

	@Override
	public void updateUser(Integer userId, UserDto userDto) {
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new UserNotFoundException("修改失敗:" + userId + "不存在");
		}
		userDto.setUserId(userId);
		User user = mapper.toUserEntity(userDto);
		userRepository.save(user);

	}

	@Override
	public void deleteUser(Integer userId) {
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new UserNotFoundException("刪除失敗:" + userId + "不存在");
		}
		userRepository.deleteById(userId);

	}

	@Override
	public void updatePassword(String account, String oldPassword, String newPassword) {
		User user = userRepository.getByAccount(account);
		if(user == null) {
			throw new UserNotFoundException("修改失敗:未找到使用者"+ account);
		}
		//比對修改之前的password是否正確
		String oldPasswordHash = Hash.getHash(oldPassword, user.getSalt());
		if(!oldPasswordHash.equals(user.getPasswordHash())) {
			throw new PasswordInvalidException("原密碼輸入錯誤");
		}
		//產生新密碼的Hash
		String newPasswordHash = Hash.getHash(newPassword, user.getSalt());
		//密碼Hash修改
		user.setPasswordHash(newPasswordHash);
		userRepository.save(user);
	
	}
	}