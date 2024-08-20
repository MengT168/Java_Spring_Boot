package com.meng.java.phoneshopnight.phoneshopnight.security;

import java.util.Optional;

public interface UserService {
	Optional<AuthUser> findUserByUserName(String username);
}
