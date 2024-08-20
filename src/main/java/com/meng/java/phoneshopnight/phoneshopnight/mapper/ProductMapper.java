package com.meng.java.phoneshopnight.phoneshopnight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductImportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.entity.ProductImportHistory;
import com.meng.java.phoneshopnight.phoneshopnight.service.ColorService;
import com.meng.java.phoneshopnight.phoneshopnight.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class , ColorService.class})
public interface ProductMapper {
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO productDTO);
	
	
	@Mapping(target = "dateImport", source = "importDTO.importDate")
	@Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id" , ignore = true)
	ProductImportHistory toProductImportHistory(ProductImportDTO importDTO , Product product);
}
