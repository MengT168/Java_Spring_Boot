package com.meng.java.phoneshopnight.phoneshopnight.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

	
	@Test
    public void showPass() {
		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("jing123");
		System.out.println(encode);
	}
}
