package com.meng.java.phoneshopnight.phoneshopnight.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ColorDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Color;
import com.meng.java.phoneshopnight.phoneshopnight.mapper.ColorMapper;
import com.meng.java.phoneshopnight.phoneshopnight.service.ColorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("colors")
@RequiredArgsConstructor
public class ColorsController {
	
	private final ColorService colorService;
	
	@PreAuthorize("hasAuthority('color:write')")
	@PostMapping
	public ResponseEntity<?> createColor(@RequestBody ColorDTO colorDTO){
		Color color = ColorMapper.INSTAND.toColor(colorDTO);
		color = colorService.create(color);
		return ResponseEntity.ok(ColorMapper.INSTAND.toColorDTO(color));
	}
	
}
