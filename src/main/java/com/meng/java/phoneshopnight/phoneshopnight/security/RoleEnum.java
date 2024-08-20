package com.meng.java.phoneshopnight.phoneshopnight.security;

import static com.meng.java.phoneshopnight.phoneshopnight.security.PermissionEnum.BRAND_READ;
import static com.meng.java.phoneshopnight.phoneshopnight.security.PermissionEnum.BRAND_WRITE;
import static com.meng.java.phoneshopnight.phoneshopnight.security.PermissionEnum.MODEL_READ;
import static com.meng.java.phoneshopnight.phoneshopnight.security.PermissionEnum.MODEL_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static com.meng.java.phoneshopnight.phoneshopnight.security.PermissionEnum.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(BRAND_WRITE , BRAND_READ , MODEL_WRITE , MODEL_READ ,COLOR_WRITE ,PRODUCT_WRITE , PRODUCT_READ  , REPORT_READ , EXPENCEREPORT_READ )),
	SALE(Set.of(BRAND_READ , MODEL_READ ));
	
	private Set<PermissionEnum> permissions;
	
	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> grandteAuthorities = this.permissions.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
			.collect(Collectors.toSet());
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+this.name());
		grandteAuthorities.add(role);
		System.out.println(grandteAuthorities);
		return grandteAuthorities;
	}
}
