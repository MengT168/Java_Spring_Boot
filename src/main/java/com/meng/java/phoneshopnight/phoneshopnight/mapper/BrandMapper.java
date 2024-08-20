package com.meng.java.phoneshopnight.phoneshopnight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.meng.java.phoneshopnight.phoneshopnight.dto.BrandDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;

@Mapper
public interface BrandMapper {
	
	BrandMapper INSTAND = Mappers.getMapper(BrandMapper.class);
	Brand toBrand(BrandDTO dto);
	BrandDTO toBrandDTO(Brand entity);
}
