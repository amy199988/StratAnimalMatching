package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.UserCert;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/login")
public class LoginController{

	@Autowired
	private CertService certService;

	
	@GetMapping
	public String loginPage() {
		return "User/login";
	}

	
	@PostMapping
	public String login(@RequestParam("account") String account, @RequestParam("password") String password,
            HttpSession session) {
		//取得憑證
		UserCert userCert =  certService.getCert(account, password);
		//放入憑證
		session.setAttribute("userCert", userCert);
		return "home"; //首頁
	}
	
}
