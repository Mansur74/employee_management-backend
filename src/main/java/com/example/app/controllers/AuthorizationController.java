package com.example.app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dtos.AccessTokenDto;
import com.example.app.dtos.AuthRequest;
import com.example.app.dtos.JwtResponse;
import com.example.app.dtos.UserDto;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.security.JwtService;
import com.example.app.services.abstracts.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthorizationController {
	
	@Autowired
	public UserService userService;
	@Autowired
    private JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;


    @PostMapping("/authorization/sign-up")
    public ResponseEntity<DataResult<UserDto>>saveUser(@Valid @RequestBody UserDto user) {
    	DataResult<UserDto> userDto = userService.createUser(user);
        return new ResponseEntity<DataResult<UserDto>>(userDto, HttpStatus.CREATED);
    }


    @PostMapping("/authorization/sign-in")
    public ResponseEntity<DataResult<JwtResponse>> AuthenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
           JwtResponse response = JwtResponse.builder()
                   .accessToken(jwtService.GenerateToken(authRequest.getEmail(), true))
                   .refreshToken(jwtService.GenerateToken(authRequest.getEmail(), false)).build();
           return new ResponseEntity<>(new SuccessDataResult<JwtResponse>(response), HttpStatus.OK);

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

    }

    @GetMapping("/authorization/accessToken")
    public ResponseEntity<DataResult<AccessTokenDto>> refreshToken(HttpServletRequest request){
    	String authHeader = request.getHeader("Authorization");
    	DataResult<AccessTokenDto> accessToken = jwtService.getAccessToken(authHeader);
    	return new ResponseEntity<DataResult<AccessTokenDto>>(accessToken, HttpStatus.OK);
    }

}
