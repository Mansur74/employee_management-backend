package com.example.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.app.dtos.AccessTokenDto;
import com.example.app.results.DataResult;
import com.example.app.results.ErrorResult;
import com.example.app.results.Result;
import com.example.app.results.SuccessDataResult;
import com.example.app.results.SuccessResult;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {
	
	@Autowired
    CustomUserDetailsService customUserDetailsService;
	
	public static List<String> refreshTokens = new ArrayList<>();  

    public static final String SECRET_ACCESS_TOKEN = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
    public static final String SECRET_REFRESH_TOKEN = "857638794F423F4428472B4K6250655368566D597133725677397Y2443261673";

    public String extractUsername(String token, boolean isAccessToken) {
        return extractClaim(token, Claims::getSubject, isAccessToken);
    }

    public Date extractExpiration(String token, boolean isAccessToken) {
        return extractClaim(token, Claims::getExpiration, isAccessToken);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, boolean isAccessToken) {
        final Claims claims = extractAllClaims(token, isAccessToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, boolean isAccessToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey(isAccessToken))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token, boolean isAccessToken) {
        return extractExpiration(token, isAccessToken).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails, boolean isAccessToken) {
    	if(!isAccessToken && !refreshTokens.stream().anyMatch(t -> t.equals(token)))
    		return false;
        final String username = extractUsername(token, isAccessToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, isAccessToken));
    }


    public String GenerateToken(String username, boolean isAccessToken){
        Map<String, Object> claims = new HashMap<>();
        String result = createToken(claims, username, isAccessToken);
        if(!isAccessToken)
        	refreshTokens.add(result);
        return result;
    }


    private String createToken(Map<String, Object> claims, String email, boolean isAccessToken) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(isAccessToken ? System.currentTimeMillis()+1000*60*60 : System.currentTimeMillis()+Integer.MAX_VALUE))
                .signWith(getSignKey(isAccessToken), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey(boolean isAccessToken) {
        byte[] keyBytes = Decoders.BASE64.decode(isAccessToken ? SECRET_ACCESS_TOKEN : SECRET_REFRESH_TOKEN);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public DataResult<AccessTokenDto> getAccessToken(String authHeader)
    {
        if(authHeader != null)
        {
        	String refreshToken = null;
            String email = null;
            
            if(authHeader != null && authHeader.startsWith("Bearer ")){
            	refreshToken = authHeader.substring(7);
            	email = extractUsername(refreshToken, false);
            }
        	
        	UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        	AccessTokenDto accessTokenDto =  AccessTokenDto.builder().accessToken(GenerateToken(email, true)).build();
        	if(validateToken(refreshToken, userDetails, false)){
        		return new SuccessDataResult<AccessTokenDto>(accessTokenDto);  
        	}
        	else {
                throw new RuntimeException("Refresh token is not valid");
        	}
        }
        else {
        	throw new RuntimeException("Refresh token can not be empty");
		}
    }
    
    public Result logout(String authHeader)
    {

    	String refreshToken = null;
        String email = null;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
        	return new ErrorResult("Authorization header can not be empty"); 
        }
        
        refreshToken = authHeader.substring(7);
        email = extractUsername(refreshToken, false);
        final String token = refreshToken;

        if(refreshTokens.stream().anyMatch(t -> t.equals(token))){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            if(validateToken(refreshToken, userDetails, false)){
            	refreshTokens = refreshTokens.stream().filter(t -> !t.equals(token)).collect(Collectors.toList());
            	return new SuccessResult("Loggoed out successfully.");
            }
            else 
            	return new ErrorResult("Refresh token is not valid"); 

        }
        return new ErrorResult("The refresh token does not exists"); 

    }
}
