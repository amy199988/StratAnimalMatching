package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.ImgurService;

@Controller
public class ImgurController {
	
	@Autowired
    private ImgurService imgurService;

    // 上傳圖片，並將圖片 URL 傳遞給 JSP 顯示
    @PostMapping("/cat_add")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        String imageUrl = imgurService.uploadImage(file);
        model.addAttribute("imageUrl", imageUrl);
        return "adoption";
    }
}
