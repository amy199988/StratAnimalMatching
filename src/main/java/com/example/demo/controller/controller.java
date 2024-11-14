package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
	
	@GetMapping("/adoptionrequest")
	public String adoptioncat() {
		return "adoptionrequest";
	}
	
	@GetMapping("/donation")
	public String donation() {
		return "donation";
	}
	
	@GetMapping("/report")
	public String report() {
		return "report";
	}
	
	@GetMapping("/lovemom")
	public String lovemom() {
		return "/User/lovemom";
	}
	
	@GetMapping("/sign_up")
	public String sign_up() {
		return "/User/sign_up";
	}
	
	@GetMapping("/manager")
	public String manager() {
		return "/Manager/manager";
	}
	
	@GetMapping("/Allcat_list")
	public String Allcat_list() {
		return "/Manager/Allcat_list";
	}
}
