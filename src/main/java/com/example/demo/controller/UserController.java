package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;

@RestController
@RequestMapping({"/user"})
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public void addUser(@RequestBody UserDto userDto,String password){
		userService.addUser(userDto, password);
	}
	
	@GetMapping("/{userAccount}")
	public void updateUser(@PathVariable Integer userId,@RequestBody UserDto userDto) {
		userService.updateUser(userId, userDto);
	
	}
	
}
