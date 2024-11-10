package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.service.AdoptionCatService;
import com.example.demo.service.ImgurService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdoptionCatController {
	
	@Autowired
	private AdoptionCatService adoptionCatService;
	@Autowired
	private ImgurService imgurService;
	
	@GetMapping("/adoption")
	public String adoption(HttpSession session) {
		if (session == null) {
            System.out.println("Session is null");
        }
		List<CatDto> catDtos = adoptionCatService.FindAllAdoptionCat();
		session.setAttribute("catDtos", catDtos);
		return "adoption";
	}
	
	@GetMapping("/cat_list")
	public String catlist(HttpSession session) {
		if (session == null) {
            System.out.println("Session is null");
        }
		List<CatDto> catDtos = adoptionCatService.FindAllCat();
		session.setAttribute("catDtos", catDtos);
		return "cat_list";
	}
	
	@GetMapping("/cat/add")
	public String catadd() {
		return "cat_add";
	}
	
	@PostMapping("/cat/add")
	public String appendCat(Cat cat, @RequestParam("photo") MultipartFile photofile, 
							HttpSession session) {
		if (session == null) {
            System.out.println("Session is null");
        }
		cat = adoptionCatService.appendCat(cat,photofile);
		session.setAttribute("cat", cat);
		return "/cat_list";
	}
	
}
