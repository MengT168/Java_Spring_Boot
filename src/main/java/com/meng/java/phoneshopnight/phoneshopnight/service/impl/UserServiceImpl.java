package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Role;
import com.meng.java.phoneshopnight.phoneshopnight.entity.User;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ApiException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.UserRepository;
import com.meng.java.phoneshopnight.phoneshopnight.security.AuthUser;
import com.meng.java.phoneshopnight.phoneshopnight.security.UserService;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;

	@Override
	public Optional<AuthUser> findUserByUserName(String name) {
		User user = userRepository.findByUsername(name).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND, "Username Not Found"));
		
		AuthUser authUser = AuthUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
//				.authorities(user.getRole().getAuthorities())
				.authorities(getAuthorities(user.getRoles()))
				.accountNonExpired(user.isAccountNonExpired())
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.build();
		
		return Optional.ofNullable(authUser);
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		Set<SimpleGrantedAuthority> authorities1 = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toSet());
		 Set<SimpleGrantedAuthority> authorities = roles.stream()
				.flatMap(role-> toStream(role))
				.collect(Collectors.toSet());
		 authorities.addAll(authorities1);
		 return authorities;
	}
	
	private Stream<SimpleGrantedAuthority> toStream(Role role){
	   return role.getPermissions().stream().map(permission-> new SimpleGrantedAuthority(permission.getName()));
	}

}
