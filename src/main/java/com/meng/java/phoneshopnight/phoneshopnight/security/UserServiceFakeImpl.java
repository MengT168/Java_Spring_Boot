package com.meng.java.phoneshopnight.phoneshopnight.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {

	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<AuthUser> findUserByUserName(String username) {
		List<AuthUser> users = List.of(
				new AuthUser("Ming", passwordEncoder.encode("Ming123"), RoleEnum.ADMIN.getAuthorities(), true, true,
						true, true),
				new AuthUser("Jing", passwordEncoder.encode("Jing123"), RoleEnum.SALE.getAuthorities(), true, true,
						true, true));

		return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
	}

}
