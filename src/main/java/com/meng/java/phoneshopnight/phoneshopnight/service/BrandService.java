package com.meng.java.phoneshopnight.phoneshopnight.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long id);
	void DeleteById(Long id);
	Brand update(Long id , Brand brand);
//	List<Brand> getBrands();
	List<Brand> getBrands(String name);
	//List<Brand> getBrands(Map<String, String> params);
	Page<Brand> getBrands(Map<String, String> params);
}
