package com.meng.java.phoneshopnight.phoneshopnight.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductImportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;

public interface ProductService {
	Product create(Product product);
	Product getById(Long id);
	void importProduct(ProductImportDTO importDTO);
	void setSellPrice(Long id , BigDecimal price);

	
	
	
	void ValidateStock(Long ProductId , Integer numberOfUnits);
	Map<Integer, String> uploadProduct(MultipartFile file);
	Product getByModelIdAndColorId(Long modelId , Long colorId);
}
