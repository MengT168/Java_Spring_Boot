package com.meng.java.phoneshopnight.phoneshopnight.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    PasswordEncoder getPasswordEncoder() {
		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return new BCryptPasswordEncoder();
	}
}
