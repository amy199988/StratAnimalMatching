package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PasswordInvalidException;
import com.example.demo.exception.UserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertService;
import com.example.demo.util.Hash;

@Service
public class CertServiceImpl implements CertService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserCert getCert(String account,String password) throws UserException {
		//1.是否有此人
		User user = userRepository.getByAccount(account);
		if(user == null) {
			throw new UserNotFoundException("無此使用者account：" + account);
		}
		
		//2.比對密碼
		String passwordHash = Hash.getHash(password,user.getSalt());
		if(!passwordHash.equals(user.getPasswordHash())) {
			throw new PasswordInvalidException("密碼錯誤");
		}
		
		//3.簽發憑證
		UserCert userCert = new UserCert(user.getUserId(),user.getUserName(),user.getAccount(),user.getRole());
		return userCert;
	}
	
	
}
