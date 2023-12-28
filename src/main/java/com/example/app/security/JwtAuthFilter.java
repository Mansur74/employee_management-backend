package com.example.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    	if(!request.getRequestURI().equals("/api/authorization/accessToken") && !request.getRequestURI().equals("/api/authorization/logout"))
    	{
    		try {
            	String authHeader = request.getHeader("Authorization");
                String token = null;
                String email = null;
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    token = authHeader.substring(7);
                    email = jwtService.extractUsername(token, true);
                }

                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
                    if(jwtService.validateToken(token, userDetails, true)){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }

                filterChain.doFilter(request, response);
    		} catch (RuntimeException e) {
                resolver.resolveException(request, response, null, e);
            }
    	}
    	else {
    		filterChain.doFilter(request, response);
		}
        
    }
}
