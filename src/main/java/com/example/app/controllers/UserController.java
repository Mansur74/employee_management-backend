package com.example.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.dtos.UserDto;
import com.example.app.services.abstracts.UserService;

@Controller
@RequestMapping("/api")
public class UserController {
	
	public UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user)
	{
		UserDto userDto = this.userService.createUser(user);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> createUser(@PathVariable("userId") int userId)
	{
		UserDto userDto = this.userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserDto> createUser(@RequestParam("email") String email)
	{
		UserDto userDto = this.userService.getUserByEmail(email);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	
	
}
