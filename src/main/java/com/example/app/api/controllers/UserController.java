package com.example.app.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.business.abstracts.UserService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.UserDto;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<DataResult<List<UserDto>>> getAllUsers() {
    	DataResult<List<UserDto>> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);      
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{userId}")
	public ResponseEntity<DataResult<UserDto>> getUserById(@PathVariable("userId") int userId)
	{
		DataResult<UserDto> userDto = this.userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}

	
}
