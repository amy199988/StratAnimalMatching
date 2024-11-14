package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.LovehomeDto;
import com.example.demo.model.entity.Lovehome;
import com.example.demo.service.LovehomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LovehomeController {
	
	@Autowired
	private LovehomeService lovehomeService;
	
	@GetMapping("/lovehome")
	public String lovehome(HttpSession session) {
		if (session == null) {
            System.out.println("Session is null");
        }
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		session.setAttribute("lovehomeDtos", lovehomeDtos);
		return "/Lovehome/lovehome";
	}
	
	@GetMapping("/lovehome/add")
	public String lovehomeadd() {
		return "lovehome_add";
	}
	
	@PostMapping("/lovehome/add")
	public String appendLovehome(Lovehome lovehome, @RequestParam("photo") MultipartFile photoFile, 
							Model model) {
		if (model == null) {
            System.out.println("Model is null");
        }
		lovehome = lovehomeService.addLovehome(lovehome, photoFile);
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		model.addAttribute("lovehomeDtos", lovehomeDtos);
		return "/Lovehome/lovehome";
	}
	
	@GetMapping("/lovehome/setting")
	public String lovehomeSetting(@RequestParam Integer lovehomeId, Model model) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		model.addAttribute("lovehomeDto", lovehomeDto);
		return "/Lovehome/lovehome_setting";
	}
	
	@GetMapping("/lovehome/update")
	public String updateLovehome(@RequestParam Integer lovehomeId, Model model) {
		LovehomeDto lovehomeDto = lovehomeService.getLovehomeById(lovehomeId);
		model.addAttribute("lovehomeDto", lovehomeDto);
		return "/Lovehome/lovehome_update";
	}
	
	@PostMapping("/lovehome/update")
	public String updateLovehome(@RequestParam Integer lovehomeId, Lovehome lovehome, 
			@RequestParam("photo") MultipartFile photoFile, Model model) {
		if(photoFile == null || photoFile.isEmpty())
		{
			lovehome = lovehomeService.updateLovehomeWithoutPhoto(lovehome);
			List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
			model.addAttribute("lovehomeDtos", lovehomeDtos);
			return "/Lovehome/lovehome_setting";
		}
		lovehome = lovehomeService.updateLovehome(lovehome, photoFile);
		List<LovehomeDto> lovehomeDtos = lovehomeService.findAllLovehomes();
		model.addAttribute("lovehomeDtos", lovehomeDtos);
		return "/Lovehome/lovehome_setting";
	}
	
}
