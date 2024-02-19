package com.meng.java.phoneshopnight.phoneshopnight.service;

import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id , Brand brand);
}
