package com.example.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dtos.AuthRequest;
import com.example.app.dtos.JwtResponse;
import com.example.app.dtos.RefreshTokenDto;
import com.example.app.dtos.UserDto;
import com.example.app.models.RefreshToken;
import com.example.app.models.User;
import com.example.app.results.DataResult;
import com.example.app.results.SuccessDataResult;
import com.example.app.security.services.JwtService;
import com.example.app.security.services.RefreshTokenService;
import com.example.app.services.abstracts.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
    private JwtService jwtService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @PostMapping("/user/sign-up")
    public ResponseEntity<DataResult<UserDto>>saveUser(@RequestBody UserDto user) {
    	DataResult<UserDto> userDto = userService.createUser(user);
        return new ResponseEntity<DataResult<UserDto>>(userDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<DataResult<List<UserDto>>> getAllUsers() {
    	DataResult<List<UserDto>> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);      
    }
    
    @GetMapping("/user/{userId}")
	public ResponseEntity<DataResult<UserDto>> getUserById(@PathVariable("userId") int userId)
	{
		DataResult<UserDto> userDto = this.userService.getUserById(userId);
		return new ResponseEntity<DataResult<UserDto>>(userDto, HttpStatus.OK);
	}

    @PostMapping("/user/login")
    public JwtResponse AuthenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
           return JwtResponse.builder()
                   .accessToken(jwtService.GenerateToken(authRequest.getUsername()))
                   .refreshToken(refreshToken.getToken()).build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

    }


    @PostMapping("/user/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenDto refreshTokenDto){
        return refreshTokenService.findByToken(refreshTokenDto.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenDto.getRefreshToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

	
	
	
	
}
