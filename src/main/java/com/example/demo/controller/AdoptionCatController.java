package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.dto.CatDto;
import com.example.demo.model.entity.Cat;
import com.example.demo.service.AdoptionCatService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdoptionCatController {
	
	@Autowired
	private AdoptionCatService adoptionCatService;
	
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
	public String catlist() {
		List<CatDto> catDtos = adoptionCatService.FindAllCat();
		return "cat_list";
	}
	
	/*@PostMapping("/cat_add")
	public String appendCat(Cat cat) {
		cat = adoptionCatService.appendCat(cat.getCatname(), cat.getBreed(), cat.getAge(), 
										   cat.getHealthStatus(), cat.getDescription(), 
										   cat.getCatphoto_url(), cat.getIsapply());
		
		return "/cat_list";
	}*/
	
}
