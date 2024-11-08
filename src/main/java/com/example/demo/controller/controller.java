package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
	
	@GetMapping("/adoption")
	public String adoption() {
		return "adoption";
	}
	
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
		return "lovemom";
	}
	
	@GetMapping("/cat_add")
	public String catadd() {
		return "cat_add";
	}
	
	@GetMapping("/lovehome")
	public String lovehome() {
		return "lovehome";
	}
	
	@GetMapping("/cat_list")
	public String catlist() {
		return "cat_list";
	}
	
	@GetMapping("/cat_update")
	public String catupdate() {
		return "cat_update";
	}
}
