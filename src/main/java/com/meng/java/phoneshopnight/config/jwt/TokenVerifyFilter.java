package com.meng.java.phoneshopnight.config.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.meng.java.phoneshopnight.phoneshopnight.exception.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenVerifyFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String secretKey = "abcjshdfbhsjf123jshdfbjsfhbsh123shjbdbsjhfbsjhf323";
		String token = authorizationHeader.replace("Bearer", "");
		
		try {
			Jws<Claims> claimsJws = Jwts.parser()
					.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
					.parseClaimsJws(token);
				
				Claims body = claimsJws.getBody();
				String username = body.getSubject();
				List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
				
				Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
					.map(x -> new SimpleGrantedAuthority(x.get("authority")))
					.collect(Collectors.toSet());
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
		}catch (ExpiredJwtException e) {
			log.info(e.getMessage());
			throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		filterChain.doFilter(request, response);
		
	}
	
}
