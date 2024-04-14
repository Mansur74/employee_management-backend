package com.example.app.api.controllers;

import java.util.List;

import com.example.app.core.utilities.results.Result;
import com.example.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.example.app.business.abstracts.UserService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.UserDto;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<DataResult<List<UserDto>>> getAllUsers() {
    	DataResult<List<UserDto>> result = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{userId}")
	public ResponseEntity<DataResult<UserDto>> getUserById(@PathVariable("userId") int userId)
	{
		DataResult<UserDto> result = this.userService.getUserById(userId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	@GetMapping("/user/me")
	public ResponseEntity<DataResult<UserDto>> authorization(@AuthenticationPrincipal User user) {
		DataResult<UserDto> userDto = this.userService.getUserByEmail(user.getUsername());
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}

	@PutMapping("/user/me")
	public ResponseEntity<DataResult<UserDto>> updateMe(@AuthenticationPrincipal User user, @RequestBody UserDto userDto)
	{
		DataResult<UserDto> userEntity = userService.getUserByEmail(user.getUsername());
		int userId = userEntity.getData().getId();
		DataResult<UserDto> result = this.userService.updateUser(userId, userDto);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@DeleteMapping("/user/me")
	public ResponseEntity<Result> deleteMe(@AuthenticationPrincipal User user)
	{
		DataResult<UserDto> userEntity = userService.getUserByEmail(user.getUsername());
		int userId = userEntity.getData().getId();
		Result result = this.userService.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	
}
