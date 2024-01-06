package com.example.app.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.business.abstracts.UserService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.dtos.AccessTokenDto;
import com.example.app.dtos.AuthRequestDto;
import com.example.app.dtos.JwtResponseDto;
import com.example.app.dtos.UserDto;
import com.example.app.security.JwtService;

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
    public ResponseEntity<Result>saveUser(@Valid @RequestBody UserDto user) {
    	Result result = userService.createUser(user);
        return new ResponseEntity<Result>(result, HttpStatus.CREATED);
    }


    @PostMapping("/authorization/sign-in")
    public ResponseEntity<DataResult<JwtResponseDto>> AuthenticateAndGetToken(@Valid @RequestBody AuthRequestDto authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
           JwtResponseDto response = JwtResponseDto.builder()
                   .accessToken(jwtService.GenerateToken(authRequest.getEmail(), true))
                   .refreshToken(jwtService.GenerateToken(authRequest.getEmail(), false)).build();
           return new ResponseEntity<>(new SuccessDataResult<JwtResponseDto>(response), HttpStatus.OK);

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }

    }
    
    @GetMapping("/authorization/authorize")
    public ResponseEntity<DataResult<UserDto>> authorization(@AuthenticationPrincipal User user) {
		DataResult<UserDto> userDto = this.userService.getUserByEmail(user.getUsername());
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/authorization/accessToken")
    public ResponseEntity<DataResult<AccessTokenDto>> refreshToken(HttpServletRequest request){
    	String authHeader = request.getHeader("Authorization");
    	DataResult<AccessTokenDto> accessToken = jwtService.getAccessToken(authHeader);
    	return new ResponseEntity<DataResult<AccessTokenDto>>(accessToken, HttpStatus.OK);
    }
    
    @DeleteMapping("/authorization/logout")
    public ResponseEntity<Result> logout(HttpServletRequest request){
    	String authHeader = request.getHeader("Authorization");
    	return ResponseEntity.status(HttpStatus.OK).body(jwtService.logout(authHeader));
    }

}
