package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		List<CatDto> catDtos = adoptionCatService.findAllAdoptionCats();
		session.setAttribute("catDtos", catDtos);
		return "adoption";
	}
	
	@GetMapping("/cat_list")
	public String catlist(HttpSession session) {
		if (session == null) {
            System.out.println("Session is null");
        }
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		session.setAttribute("catDtos", catDtos);
		return "cat_list";
	}
	
	@GetMapping("/cat/add")
	public String catadd() {
		return "cat_add";
	}
	
	@PostMapping("/cat/add")
	public String appendCat(Cat cat, @RequestParam("photo") MultipartFile photoFile, 
							Model model) {
		if (model == null) {
            System.out.println("Model is null");
        }
		cat = adoptionCatService.addCat(cat,photoFile);
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		model.addAttribute("catDtos", catDtos);
		return "cat_list";
	}
	
	@GetMapping("/cat/update")
	public String updateCat(@RequestParam("catId") Integer catId, Model model) {
		CatDto catDto = adoptionCatService.getCatById(catId);
		model.addAttribute("catDto", catDto);
		return "cat_update";
	}
	
	@PostMapping("/cat/update")
	public String updateCat(@RequestParam("catId") Integer catId, Cat cat, 
			@RequestParam("photo") MultipartFile photoFile, Model model) {
		if(photoFile == null || photoFile.isEmpty())
		{
			cat = adoptionCatService.updateCatWithoutPhoto(cat);
			model.addAttribute("cat", cat);
			List<CatDto> catDtos = adoptionCatService.findAllCats();
			model.addAttribute("catDtos", catDtos);
			return "cat_list";
		}
		cat = adoptionCatService.updateCat(cat, photoFile);
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		model.addAttribute("catDtos", catDtos);
		return "cat_list";
	}
	
	@GetMapping("/cat/delete")
	public String deleteCat(@RequestParam("catId") Integer catId, Model model) {
		adoptionCatService.deleteCatById(catId);
		List<CatDto> catDtos = adoptionCatService.findAllCats();
		model.addAttribute("catDtos", catDtos);
		return "cat_list";
	}
}
