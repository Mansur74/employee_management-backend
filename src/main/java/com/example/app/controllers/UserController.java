package com.example.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dtos.AccessTokenDto;
import com.example.app.dtos.AuthRequest;
import com.example.app.dtos.JwtResponse;
import com.example.app.dtos.UserDto;
import com.example.app.models.UserEntity;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.security.helpers.CustomUserDetailsService;
import com.example.app.security.services.JwtService;
import com.example.app.services.abstracts.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserService userService;
	@Autowired
    private JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @PostMapping("/user/sign-up")
    public ResponseEntity<DataResult<UserDto>>saveUser(@Valid @RequestBody UserDto user) {
    	DataResult<UserDto> userDto = userService.createUser(user);
        return new ResponseEntity<DataResult<UserDto>>(userDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<DataResult<List<UserDto>>> getAllUsers() {
    	DataResult<List<UserDto>> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);      
    }
    
 
    @GetMapping("/me")
    public ResponseEntity<User> getMe(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);      
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{userId}")
	public ResponseEntity<DataResult<UserDto>> getUserById(@PathVariable("userId") int userId)
	{
		DataResult<UserDto> userDto = this.userService.getUserById(userId);
		return new ResponseEntity<DataResult<UserDto>>(userDto, HttpStatus.OK);
	}

    @PostMapping("/user/login")
    public ResponseEntity<DataResult<JwtResponse>> AuthenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
           JwtResponse response = JwtResponse.builder()
                   .accessToken(jwtService.GenerateToken(authRequest.getUsername(), true))
                   .refreshToken(jwtService.GenerateToken(authRequest.getUsername(), false)).build();
           return new ResponseEntity<>(new SuccessDataResult<JwtResponse>(response), HttpStatus.OK);

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

    }


    @GetMapping("/user/accessToken")
    public ResponseEntity<DataResult<AccessTokenDto>> refreshToken(HttpServletRequest request){
    	String authHeader = request.getHeader("Authorization");
    	DataResult<AccessTokenDto> accessToken = jwtService.getAccessToken(authHeader);
    	return new ResponseEntity<DataResult<AccessTokenDto>>(accessToken, HttpStatus.OK);
    }

	
}
