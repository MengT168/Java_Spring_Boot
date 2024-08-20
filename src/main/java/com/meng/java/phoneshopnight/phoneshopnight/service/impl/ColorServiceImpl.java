package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Color;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ColorRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
	
	private final ColorRepository colorRepository;

	@Override
	public Color create(Color color) {
		return colorRepository.save(color);
	}

	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Color", id));
	}



}
