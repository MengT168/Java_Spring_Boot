package com.meng.java.phoneshopnight.phoneshopnight.service.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ModelDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Model;
import com.meng.java.phoneshopnight.phoneshopnight.mapper.ModelEntityMapper;
import com.meng.java.phoneshopnight.phoneshopnight.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelController {
	
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	
	@RolesAllowed("ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		Model model = modelMapper.toModel(dto);
		model = modelService.save(model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}
	
}
