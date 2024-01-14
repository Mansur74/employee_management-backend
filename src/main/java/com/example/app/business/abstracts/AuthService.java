package com.example.app.business.abstracts;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.Result;
import com.example.app.dtos.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public DataResult<JwtResponseDto> signIn(AuthRequestDto authRequest);
    public DataResult<AccessTokenDto> getAccessToken(RefreshTokenDto body);
    public Result signOut(RefreshTokenDto body);
}
