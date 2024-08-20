package com.meng.java.phoneshopnight.phoneshopnight.service.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meng.java.phoneshopnight.phoneshopnight.dto.PriceDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ProductImportDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Product;
import com.meng.java.phoneshopnight.phoneshopnight.mapper.ProductMapper;
import com.meng.java.phoneshopnight.phoneshopnight.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
	
	
	private final ProductService productService;	
	private final ProductMapper productMapper;
	@PreAuthorize("hasAuthority('product:write')")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
		Product product = productMapper.toProduct(productDTO);
		product= productService.create(product);
		return ResponseEntity.ok(product);
	}
	
	
	@PreAuthorize("hasAuthority('product:read')")
	@GetMapping("{id}")
	public ResponseEntity<?> getByID(@PathVariable("id") Long productId){
		Product product = productService.getById(productId);
		return ResponseEntity.ok(product);
	}
	
	
	
	@PostMapping("importProduct")
	public ResponseEntity<?>  importProduct(@RequestBody @Valid ProductImportDTO productImportDTO){
		productService.importProduct(productImportDTO);
		return ResponseEntity.ok().build();
	}
	
	
	
	@PostMapping("{productId}/setSalePrice")
	public ResponseEntity<?> setSalePrice(@PathVariable Long productId , @RequestBody PriceDTO priceDTO){
		productService.setSellPrice(productId, priceDTO.getPrice());
		return ResponseEntity.ok().build();
	}
	
	
	
	@PostMapping("uploadProduct")
	public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file){
		Map<Integer, String> errorMap = productService.uploadProduct(file);
		return ResponseEntity.ok(errorMap);
	}
}
