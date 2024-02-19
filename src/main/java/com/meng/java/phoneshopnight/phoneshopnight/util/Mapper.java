package com.meng.java.phoneshopnight.phoneshopnight.util;

import com.meng.java.phoneshopnight.phoneshopnight.dto.BrandDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;

public class Mapper {
	public static Brand toBrand(BrandDTO dto) {
		Brand brand = new Brand();
//		brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}
	
	public static BrandDTO toBrandDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setName(brand.getName());
		return brandDTO;
	}
}
