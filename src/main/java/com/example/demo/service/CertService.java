package com.example.demo.service;

import com.example.demo.exception.PasswordInvalidException;
import com.example.demo.model.dto.UserCert;

public interface CertService {

	public UserCert getCert(String account,String password) throws PasswordInvalidException;
}
