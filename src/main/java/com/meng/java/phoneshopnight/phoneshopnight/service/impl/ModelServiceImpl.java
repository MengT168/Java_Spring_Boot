package com.meng.java.phoneshopnight.phoneshopnight.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Model;
import com.meng.java.phoneshopnight.phoneshopnight.exception.ResourceNotFoundException;
import com.meng.java.phoneshopnight.phoneshopnight.repository.ModelRepository;
import com.meng.java.phoneshopnight.phoneshopnight.service.ModelService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
	
	private ModelRepository modelRepository;
//	private BrandService brandService;
	
	@Override
	public Model save(Model model) {
//		Integer brandId = model.getBrand().getId();
//		brandService.getById(brandId);
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrandId(Long brandId) {
		return modelRepository.findByBrandId(brandId);
	}

	@Override
	public Model getById(Long id) {
		return modelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Model", id));
	}


	
	
}