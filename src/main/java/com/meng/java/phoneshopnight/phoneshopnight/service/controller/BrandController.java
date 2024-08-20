package com.meng.java.phoneshopnight.phoneshopnight.service.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meng.java.phoneshopnight.phoneshopnight.dto.BrandDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.ModelDTO;
import com.meng.java.phoneshopnight.phoneshopnight.dto.PageDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Brand;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Model;
import com.meng.java.phoneshopnight.phoneshopnight.mapper.BrandMapper;
import com.meng.java.phoneshopnight.phoneshopnight.mapper.ModelEntityMapper;
import com.meng.java.phoneshopnight.phoneshopnight.service.BrandService;
import com.meng.java.phoneshopnight.phoneshopnight.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("brands")
public class BrandController {
	
	
	private final BrandService brandService;
	private final ModelService modelService;	
	private final ModelEntityMapper entityMapper;
	
	
	@PreAuthorize("hasAuthority('brand:write')")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
	//Mapper manual
//		Brand brand = Mapper.toBrand(brandDTO); 
		Brand brand = BrandMapper.INSTAND.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(brand));
	}
	
	@PreAuthorize("hasAuthority('brand:read')")
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(brand));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long brandId , @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTAND.toBrand(brandDTO);// push in with id
		Brand update = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(update));// out without id
	}
	
//	@GetMapping
//	public ResponseEntity<?> getBrands(){
//		
//		List<BrandDTO> list = brandService.getBrands()
//				.stream()
//				.map(brand -> BrandMapper.INSTAND.toBrandDTO(brand))
//				.collect(Collectors.toList());
//		
//		return ResponseEntity.ok(list);
////		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(brand));
//	}
	
	@GetMapping("filter")
	public ResponseEntity<?> getBrandss(@RequestParam("name") String name){
		
		List<BrandDTO> list = brandService.getBrands(name)
				.stream()
				.map(brand -> BrandMapper.INSTAND.toBrandDTO(brand))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(list);
	}
	
	@PreAuthorize("hasAuthority('brand:read')")
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		
		Page<Brand> page = brandService.getBrands(params);
		
		PageDTO pageDTO = new PageDTO(page);
		
//		List<BrandDTO> list = brandService.getBrands(params)
//				.stream()
//				.map(brand -> BrandMapper.INSTAND.toBrandDTO(brand))
//				.collect(Collectors.toList());
		
//		return ResponseEntity.ok(list);
		return ResponseEntity.ok(pageDTO);
//		return ResponseEntity.ok(BrandMapper.INSTAND.toBrandDTO(brand));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long brandId){
		brandService.DeleteById(brandId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelByBrand(@PathVariable("id") Long brandId){
		List<Model> brands = modelService.getByBrandId(brandId);
		List<ModelDTO> list = brands.stream()
			.map(model->entityMapper.toModelDTO(model))
			.toList();
		return ResponseEntity.ok(list);
	}
	
	
	
	
}
