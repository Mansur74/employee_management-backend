package com.example.app.business.concretes;

import com.example.app.business.abstracts.AuthService;
import com.example.app.business.abstracts.UserDetailService;
import com.example.app.business.abstracts.UserService;
import com.example.app.core.utilities.results.*;
import com.example.app.dataAccess.abstracts.UserDao;
import com.example.app.dataAccess.abstracts.UserDetailDao;
import com.example.app.dtos.*;
import com.example.app.entities.UserDetail;
import com.example.app.entities.UserEntity;
import com.example.app.mappers.UserMapper;
import com.example.app.security.CustomUserDetailsService;
import com.example.app.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.example.app.mappers.UserMapper.mapToUser;

@Service
public class AuthManager implements AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    public Result signUp(UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto).getData();
        UserDetailDto userDetail = UserDetailDto.builder().build();
        userDetailService.createUserDetail(userDetail, createdUser.getId());
        return new SuccessResult("Successfully signed up");
    }

    @Override
    public DataResult<JwtResponseDto> signIn(AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            JwtResponseDto response = JwtResponseDto.builder()
                    .accessToken(jwtService.GenerateToken(authRequest.getEmail(), true))
                    .refreshToken(jwtService.GenerateToken(authRequest.getEmail(), false)).build();
            return new SuccessDataResult<JwtResponseDto>(response);

        } else {
            throw new UsernameNotFoundException("Username or password is invalid");
        }
    }

    @Override
    public DataResult<AccessTokenDto> getAccessToken(RefreshTokenDto body) {
        String refreshToken = body.getRefreshToken();
        String email = jwtService.extractUsername(refreshToken, false);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        AccessTokenDto accessTokenDto =  AccessTokenDto.builder().accessToken(jwtService.GenerateToken(email, true)).build();
        if(jwtService.validateToken(refreshToken, userDetails, false)){
            return new SuccessDataResult<AccessTokenDto>(accessTokenDto);
        }
        else {
            throw new RuntimeException("Refresh token is not valid");
        }
    }

    @Override
    public Result signOut(RefreshTokenDto body) {
        String refreshToken = body.getRefreshToken();
        String email = jwtService.extractUsername(refreshToken, false);
        final String token = refreshToken;

        if(JwtService.refreshTokens.stream().anyMatch(t -> t.equals(token))){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            if(jwtService.validateToken(refreshToken, userDetails, false)){
                JwtService.refreshTokens = JwtService.refreshTokens.stream().filter(t -> !t.equals(token)).collect(Collectors.toList());
                return new SuccessResult("Loggoed out successfully.");
            }
            else
                return new ErrorResult("Refresh token is not valid");

        }
        return new ErrorResult("The refresh token does not exists");
    }
}
