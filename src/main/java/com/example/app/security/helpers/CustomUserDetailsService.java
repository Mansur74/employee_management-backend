package com.example.app.security.helpers;

import com.example.app.models.UserEntity;
import com.example.app.repositories.abstracts.UserDao;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userDao.findByUsername(username);
		if(user != null)
		{
			User authUser = new User(
					user.getUsername(),
					user.getPassword(),
					user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName().toUpperCase()))
					.collect(Collectors.toList())
			);
			return authUser;
		}
		else {
			{
				throw new UsernameNotFoundException("Invalid username or password!");
			}
		}
	}
}
