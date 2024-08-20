package com.meng.java.phoneshopnight.phoneshopnight.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.meng.java.phoneshopnight.config.jwt.FilterChainExceptionHandler;
import com.meng.java.phoneshopnight.config.jwt.JwtLoginFilter;
import com.meng.java.phoneshopnight.config.jwt.TokenVerifyFilter;







@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService detailsService;
	
	
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	 SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration)))
		.addFilterBefore(new FilterChainExceptionHandler(), JwtLoginFilter.class)
		.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .authorizeRequests()
        .antMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()
        // Uncomment and adjust the following lines if you want to use role-based access control
//        .antMatchers("/models").hasRole(RoleEnum.SALE.name())
//        .antMatchers(HttpMethod.POST , "/brands").hasAuthority(BRAND_WRITE.getDescription())
//        .antMatchers(HttpMethod.GET , "/brands").hasAuthority(BRAND_READ.getDescription())
        .anyRequest()
        .authenticated()
        .and()
        // Add your login page URL if you have one
        .formLogin()
        .loginProcessingUrl("/login")
        .permitAll();
		
		return http.build();
			
	}
	
	/*@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
//		User user1 = new User("Meng", passwordEncoder.encode("Meng123"),Collections.emptyList());
		
		UserDetails user1 = User.builder()
				.username("Meng")
				.password(passwordEncoder.encode("Meng123"))
//				.roles("ADMIN") //ROLE_ADMIN
				.authorities(RoleEnum.ADMIN.getAuthorities()) // collection of GrandtedAuthority
				.build();
		
		UserDetails user2 = User.builder()
				.username("Jing")
				.password(passwordEncoder.encode("Jing123"))
//				.roles(RoleEnum.SALE.name()) //ROLE_SALE
				.authorities(RoleEnum.SALE.getAuthorities())
				.build();
		
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1,user2);
		return userDetailsService;
	}*/
	
	@Bean
	AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	
	@Bean
	AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
	
}
