package com.meng.java.phoneshopnight.phoneshopnight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ModelDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Model;
import com.meng.java.phoneshopnight.phoneshopnight.service.BrandService;






@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);
	
	@Mapping(target = "brand" , source = "brandId")
	Model toModel(ModelDTO dto);
	
	
	@Mapping(target = "brandId" , source = "brand.id")
	ModelDTO toModelDTO(Model model);
	
//	default Brand toBrand(Integer brId) {
//		Brand brand = new Brand();
//		brand.setId(brId);
//		return brand;
//	}
}
