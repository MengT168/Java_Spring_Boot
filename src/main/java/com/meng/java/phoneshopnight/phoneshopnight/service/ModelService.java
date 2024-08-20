package com.meng.java.phoneshopnight.phoneshopnight.service;

import java.util.List;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Model;

public interface ModelService {
	Model save(Model model);
	List<Model> getByBrandId(Long brandId);
	
	Model getById(Long id);
}
