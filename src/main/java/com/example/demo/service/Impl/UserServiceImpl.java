package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.ObjectMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	public void addUser(UserDto userDto,String password) {
/**		String salt = Hash.getSalt();
		String passswordHash = Hash.getHash(password,salt);
		Boolean active = false;

		Optional <User> optUser = userRepository.findByAccount(userDto.getAccount());
		if(optUser.isPresent()) {
			throw new UserAlreadyExistsException("新增失敗"+userDto.getAccount()+"已存在");
		}
		User user = objectMapper.toUserEntity(userDto);
		userRepository.save(user);
*/		
=======
	public void addUser(UserDto userDto) {
		String salt = Hash.getSalt();
		//String passswordHash = Hash.getHash();

		Optional<User> optUser = userRepository.findByAccount(userDto.getAccount());
		if (optUser.isPresent()) {
			throw new UserAlreadyExistsException("新增失敗" + userDto.getAccount() + "已存在");
		}

>>>>>>> Stashed changes
=======
	public void addUser(UserDto userDto) {
		String salt = Hash.getSalt();
		//String passswordHash = Hash.getHash();

		Optional<User> optUser = userRepository.findByAccount(userDto.getAccount());
		if (optUser.isPresent()) {
			throw new UserAlreadyExistsException("新增失敗" + userDto.getAccount() + "已存在");
		}

>>>>>>> Stashed changes
	}

	
	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(objectMapper::toUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("無此使用者userId：" + userId));
		return objectMapper.toUserDto(user);
	}

	@Override
	public UserDto getUserByAccount(String account) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		User user = userRepository.findByAccount(account);
			if (user == null) {
				throw new UserNotFoundException("無此使用者account：" + account);
				}
		return objectMapper.toUserDto(user);
		
=======
=======
>>>>>>> Stashed changes
		User user = userRepository.findByAccount(account)
				.orElseThrow(() -> new UserNotFoundException("無此使用者account：" + account));
		return objectMapper.toUserDto(user);

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	}

	@Override
	public void updateUser(Integer userId, UserDto userDto) {
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new UserNotFoundException("修改失敗:" + userId + "不存在");
		}

		userDto.setUserId(userId);
		User user = objectMapper.toUserEntity(userDto);
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
	public void updatePassword(Integer userId, String username, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

<<<<<<< Updated upstream
<<<<<<< Updated upstream

	
	
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}