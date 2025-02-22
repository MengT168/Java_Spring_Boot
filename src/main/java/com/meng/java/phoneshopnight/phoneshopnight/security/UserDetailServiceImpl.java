package com.meng.java.phoneshopnight.phoneshopnight.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userService.findUserByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
	}
	
}
