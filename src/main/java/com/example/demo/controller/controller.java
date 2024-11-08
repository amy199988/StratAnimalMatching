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
	
	@GetMapping("/catadd")
	public String catadd() {
		return "catadd";
	}
	
	@GetMapping("/lovehome")
	public String lovehome() {
		return "lovehome";
	}
	
	@GetMapping("/catlist")
	public String catlist() {
		return "catlist";
	}
}
