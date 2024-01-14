package com.example.app.api.controllers;

import com.example.app.business.abstracts.AuthService;
import com.example.app.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.business.abstracts.UserService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/authorization/sign-up")
    public ResponseEntity<Result> signUp(@Valid @RequestBody UserDto user) {
    	Result result = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PostMapping("/authorization/sign-in")
    public ResponseEntity<DataResult<JwtResponseDto>> signIn(@Valid @RequestBody AuthRequestDto authRequest){
        DataResult<JwtResponseDto> result = authService.signIn(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @PostMapping("/authorization/sign-out")
    public ResponseEntity<Result> signOut(@RequestBody RefreshTokenDto refreshToken){
        return ResponseEntity.status(HttpStatus.OK).body(authService.signOut(refreshToken));
    }
    
    @GetMapping("/authorization/me")
    public ResponseEntity<DataResult<UserDto>> authorization(@AuthenticationPrincipal User user) {
		DataResult<UserDto> userDto = this.userService.getUserByEmail(user.getUsername());
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("/authorization/accessToken")
    public ResponseEntity<DataResult<AccessTokenDto>> getAccessToken(@RequestBody RefreshTokenDto refreshToken){
    	DataResult<AccessTokenDto> accessToken = authService.getAccessToken(refreshToken);
    	return new ResponseEntity<DataResult<AccessTokenDto>>(accessToken, HttpStatus.OK);
    }

}
