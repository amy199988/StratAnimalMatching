package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PasswordInvalidException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.dto.ReportListDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Lovehome;
import com.example.demo.model.entity.ReportList;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LovehomeService;
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
	
	@Autowired
	private LovehomeService lovehomeService;

	@Override // 註冊、新增
	public void addUser(UserDto userDto) {
		User account = userRepository.getByAccount(userDto.getAccount());
		if (account != null) {
			throw new UserAlreadyExistsException(userDto.getAccount() + "已存在");
		}
		User user = objectMapper.convertValue(userDto, User.class);
		String salt = Hash.getSalt();
		String passswordHash = Hash.getHash(userDto.getPassword(), salt);

		user.setSalt(salt);
		user.setPasswordHash(passswordHash);
		if (userDto.getLovehomeDto() != null) {
			Lovehome lovehome = mapper.toLovehomeEntity(userDto.getLovehomeDto());
			user.setLovehome(lovehome);
		}
		userRepository.save(user);
	}

	@Override // 查詢所有使用者
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
	}

	@Override // 查詢單一使用者(依ID)
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("無此使用者userId：" + userId));
		UserDto userDto = mapper.toUserDto(user);
		if(user.getLovehome() == null) {
			return userDto;
		}
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(user.getLovehome().getLovehomeId());
		userDto.setLovehomeDto(lovehomeDto);
		return userDto;
	}

	@Override // 查詢單一使用者(依帳號)
	public UserDto getUserByAccount(String account) {
		Optional<User> optUser = userRepository.findByAccount(account);
		if (optUser.isEmpty()) {
			throw new UserNotFoundException("無此使用者account：" + account);
		}
		return mapper.toUserDto(optUser.get());
	}

	@Override // 更新使用者資料
	public UserDto updateUser(UserDto userDto) {
		User Updateuser = mapper.toUserEntity(userDto);
		User user = userRepository.findById(userDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("無此使用者userId：" + userDto.getUserId()));
		Updateuser.setPasswordHash(user.getPasswordHash());
		Updateuser.setSalt(user.getSalt());
		Updateuser.setActive(user.getActive());
		if(userDto.getLovehomeDto() != null) {
			userRepository.save(Updateuser);
			UserDto updateUserDto = mapper.toUserDto(Updateuser);
			updateUserDto.setLovehomeDto(userDto.getLovehomeDto());
			return updateUserDto;
		}
		userRepository.save(Updateuser);
		return mapper.toUserDto(Updateuser);
	}

	@Override // 刪除資料
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override // 更新密碼
	public void updatePassword(Integer userId, String oldPassword, String newPassword) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("無此使用者userId：" + userId));

		// 比對修改之前的password是否正確
		String oldPasswordHash = Hash.getHash(oldPassword, user.getSalt());
		if (!oldPasswordHash.equals(user.getPasswordHash())) {
			throw new PasswordInvalidException("原密碼輸入錯誤");
		}
		if (newPassword.isEmpty()) {
			throw new PasswordInvalidException("不得為空");
		}

		// 產生新密碼的Hash
		String newPasswordHash = Hash.getHash(newPassword, user.getSalt());
		// 密碼Hash修改
		user.setPasswordHash(newPasswordHash);
		userRepository.save(user);

	}

	// 查詢的通報清單
	public List<ReportListDto> getUserReportList(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		List<ReportList> reportLists = user.getReportLists();
		return reportLists.stream().map(mapper::toReportListDto).collect(Collectors.toList());

	}

}