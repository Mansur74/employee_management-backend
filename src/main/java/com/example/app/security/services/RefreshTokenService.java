package com.example.app.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.models.RefreshToken;
import com.example.app.repositories.abstracts.RefreshTokenDao;
import com.example.app.repositories.abstracts.UserDao;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenDao refreshTokenDao;

    @Autowired
    UserDao userDao;

    public RefreshToken createRefreshToken(String username){
    	RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userDao.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenDao.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenDao.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
        	refreshTokenDao.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;

    }

}
